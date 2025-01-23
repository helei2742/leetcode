package cn.com.helei.DepinBot.core.bot;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.BaseDepinWSClient;
import cn.com.helei.DepinBot.core.constants.DepinBotStatus;
import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import cn.com.helei.DepinBot.core.pool.account.AccountPool;
import cn.com.helei.DepinBot.core.pool.env.BrowserEnvPool;
import cn.com.helei.DepinBot.core.exception.DepinBotInitException;
import cn.com.helei.DepinBot.core.exception.DepinBotStartException;
import cn.com.helei.DepinBot.core.exception.DepinBotStatusException;
import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import cn.com.helei.DepinBot.core.pool.network.NetworkProxyPool;
import cn.com.helei.DepinBot.core.util.NamedThreadFactory;
import cn.com.helei.DepinBot.core.util.RestApiClientFactory;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Slf4j
@Getter
public abstract class AbstractDepinBot<Req, Resp> {
    /**
     * 执行异步任务的线程池
     */
    private final ExecutorService executorService;

    /**
     * 代理池
     */
    private final NetworkProxyPool proxyPool;

    /**
     * 浏览器环境池
     */
    private final BrowserEnvPool browserEnvPool;

    /**
     * 账户池
     */
    private final AccountPool accountPool;

    /**
     * 配置
     */
    private final BaseDepinBotConfig baseDepinBotConfig;

    /**
     * 状态
     */
    private DepinBotStatus status = DepinBotStatus.NEW;

    /**
     * 同步控制
     */
    private final Semaphore syncController;

    public AbstractDepinBot(BaseDepinBotConfig baseDepinBotConfig) {
        this.baseDepinBotConfig = baseDepinBotConfig;
        this.executorService = Executors.newThreadPerTaskExecutor(new NamedThreadFactory(baseDepinBotConfig.getName() + "-executor"));

        this.proxyPool = NetworkProxyPool.loadYamlPool(
                baseDepinBotConfig.getNetworkPoolConfig(),
                "bot.network.proxy",
                NetworkProxyPool.class
        );
        this.browserEnvPool = BrowserEnvPool.loadYamlPool(
                baseDepinBotConfig.getBrowserEnvPoolConfig(),
                "bot.browser",
                BrowserEnvPool.class
        );
        this.accountPool = AccountPool.loadYamlPool(
                baseDepinBotConfig.getAccountPoolConfig(),
                "bot.account",
                AccountPool.class
        );

        syncController = new Semaphore(baseDepinBotConfig.getConcurrentCount());
    }

    public void init() {
        updateState(DepinBotStatus.INIT);
        try {
            doInit();

            //更新状态
            updateState(DepinBotStatus.INIT_FINISH);
        } catch (Exception e) {
            log.error("初始化DepinBot[{}}发生错误", getBaseDepinBotConfig().getName(), e);
            updateState(DepinBotStatus.INIT_ERROR);
        }
    }

    /**
     * 初始化方法
     */
    protected abstract void doInit() throws DepinBotInitException;


    protected abstract void doExecute() throws IOException;

    /**
     * 使用accountContext构建AbstractDepinWSClient
     *
     * @param accountContext accountContext
     * @return AbstractDepinWSClient
     */
    public abstract BaseDepinWSClient<Req, Resp> buildAccountWSClient(AccountContext accountContext);


    /**
     * 当账户链接时调用
     *
     * @param depinWSClient depinWSClient
     * @param success       是否成功
     */
    public abstract void whenAccountConnected(BaseDepinWSClient<Req, Resp> depinWSClient, Boolean success);

    /**
     * 当ws连接收到响应
     *
     * @param depinWSClient depinWSClient
     * @param id            id
     * @param response      response
     */
    public abstract void whenAccountReceiveResponse(BaseDepinWSClient<Req, Resp> depinWSClient, String id, Resp response);

    /**
     * 当ws连接收到消息
     *
     * @param depinWSClient depinWSClient
     * @param message       message
     */
    public abstract void whenAccountReceiveMessage(BaseDepinWSClient<Req, Resp> depinWSClient, Resp message);

    /**
     * 获取心跳消息
     *
     * @param depinWSClient depinWSClient
     * @return 消息体
     */
    public abstract Req getHeartbeatMessage(BaseDepinWSClient<Req, Resp> depinWSClient);


    /**
     * 启动bot
     *
     * @throws DepinBotStartException DepinBotStartException
     */
    public void start() throws DepinBotStartException {
        updateState(DepinBotStatus.STARTING);
        log.info("正在启动Depin Bot");
        try {
            CountDownLatch startLatch = new CountDownLatch(1);
            //启动命令行交互的线程
            asyncExecute(startLatch);

            log.info("Depin Bot启动完毕");

            updateState(DepinBotStatus.RUNNING);
            startLatch.await();

        } catch (Exception e) {
            updateState(DepinBotStatus.SHUTDOWN);
            throw new DepinBotStartException("启动CommandLineDepinBot发生错误", e);
        }
    }

    /**
     * 同步请求，使用syncController控制并发
     *
     * @param proxy   proxy
     * @param url     url
     * @param method  method
     * @param headers headers
     * @param body    body
     * @param params  params
     * @return CompletableFuture<String> response str
     */
    public CompletableFuture<String> syncRequest(
            NetworkProxy proxy,
            String url,
            String method,
            Map<String, String> headers,
            JSONObject body,
            JSONObject params
    ) {
        return syncRequest(proxy, url, method, headers, body, params, null);
    }

    /**
     * 同步请求，使用syncController控制并发
     *
     * @param proxy   proxy
     * @param url     url
     * @param method  method
     * @param headers headers
     * @param body    body
     * @param params  params
     * @return CompletableFuture<String> response str
     */
    public CompletableFuture<String> syncRequest(
            NetworkProxy proxy,
            String url,
            String method,
            Map<String, String> headers,
            JSONObject body,
            JSONObject params,
            Supplier<String> requestStart
    ) {
       return CompletableFuture.supplyAsync(()->{
           try {
               syncController.acquire();
               // 随机延迟
               TimeUnit.MILLISECONDS.sleep(RandomUtil.randomLong(1000, 3000));

               String str = "开始";
               if (requestStart != null) {
                   str = requestStart.get();
               }
               log.info("同步器允许发送请求-{}", str);

               return RestApiClientFactory.getClient(proxy).request(
                       url,
                       method,
                       headers,
                       body,
                       params
               ).get();
           } catch (InterruptedException | ExecutionException e) {
               throw new RuntimeException(e);
           } finally {
               syncController.release();
           }
       }, executorService);
    }

    /**
     * 添加定时任务
     *
     * @param runnable runnable
     * @param delay    delay
     * @param timeUnit timeUnit
     */
    public void addTimer(Runnable runnable, long delay, TimeUnit timeUnit) {
        executorService.execute(() -> {
            while (true) {
                try {
                    syncController.acquire();

                    runnable.run();

                    timeUnit.sleep(delay);
                } catch (InterruptedException e) {
                    log.error("timer interrupted will stop it", e);
                    break;
                } finally {
                    syncController.release();
                }
            }
        });
    }

    /**
     * 异步启动
     */
    private void asyncExecute(CountDownLatch startLatch) {
        Thread commandInputThread = new Thread(() -> {
            try {
                doExecute();
            } catch (IOException e) {
                log.error("启动bot发生错误", e);
            } finally {
                startLatch.countDown();
            }
        }, "depin-bot-main");
        commandInputThread.setDaemon(true);
        commandInputThread.start();
    }


    /**
     * 更新DepinBotStatus
     *
     * @param newStatus 新状态
     */
    private synchronized void updateState(DepinBotStatus newStatus) throws DepinBotStatusException {
        boolean b = true;
        if (newStatus.equals(DepinBotStatus.SHUTDOWN)) {
            status = DepinBotStatus.SHUTDOWN;
            b = false;
        } else {
            b = switch (status) {
                //当前为NEW，新状态才能为NEW,SHUTDOWN
                case NEW -> DepinBotStatus.INIT.equals(newStatus);
                //当前为INIT，新状态只能为INIT_FINISH、INIT_ERROR,SHUTDOWN
                case INIT -> newStatus.equals(DepinBotStatus.INIT_FINISH)
                        || newStatus.equals(DepinBotStatus.INIT_ERROR);
                //当前为INIT_ERROR,新状态只能为ACCOUNT_LOADING, SHUTDOWN
                case INIT_ERROR -> newStatus.equals(DepinBotStatus.INIT);
                //当前状态为INIT_FINISH，新状态只能为ACCOUNT_LIST_CONNECT, SHUTDOWN
                case INIT_FINISH -> newStatus.equals(DepinBotStatus.STARTING);
                //当前状态为STARING，新状态只能为RUNNING,SHUTDOWN
                case STARTING -> newStatus.equals(DepinBotStatus.RUNNING);
                //RUNNING，新状态只能为 SHUTDOWN
                case RUNNING -> false;
                case SHUTDOWN -> false;
            };
        }


        if (b) {
            log.info("CommandLineDepinBot Status change [{}] => [{}]", status, newStatus);
            this.status = newStatus;
        } else {
            throw new DepinBotStatusException(String.format("Depin Bot Status不能从[%s]->[%s]", status, newStatus));
        }
    }
}
package cn.com.helei.DepinBot.core.bot;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.BaseDepinWSClient;
import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import cn.com.helei.DepinBot.core.dto.account.AccountPrintDto;
import cn.com.helei.DepinBot.core.dto.RewordInfo;
import cn.com.helei.DepinBot.core.pool.env.BrowserEnv;
import cn.com.helei.DepinBot.core.exception.DepinBotInitException;
import cn.com.helei.DepinBot.core.exception.DepinBotStatusException;
import cn.com.helei.DepinBot.core.netty.constants.WebsocketClientStatus;
import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import cn.com.helei.DepinBot.core.supporter.persistence.AccountPersistenceManager;
import cn.com.helei.DepinBot.core.util.NamedThreadFactory;
import cn.com.helei.DepinBot.core.util.table.CommandLineTablePrintHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

@Slf4j
public abstract class AccountAutoManageDepinBot<Req, Resp> extends AbstractDepinBot<Req, Resp> {
    /**
     * 异步操作线程池
     */
    private final ExecutorService executorService;

    /**
     * 账户客户端
     */
    private final ConcurrentMap<AccountContext, BaseDepinWSClient<Req, Resp>> accountWSClientMap = new ConcurrentHashMap<>();

    /**
     * 持久化管理器
     */
    private final AccountPersistenceManager persistenceManager = new AccountPersistenceManager();

    /**
     * 账号列表
     */
    @Getter
    private final List<AccountContext> accounts = new ArrayList<>();


    public AccountAutoManageDepinBot(BaseDepinBotConfig baseDepinBotConfig) {
        super(baseDepinBotConfig);


        this.executorService = Executors
                .newThreadPerTaskExecutor(new NamedThreadFactory(baseDepinBotConfig.getName() + "-account"));
    }

    @Override
    protected void doInit() throws DepinBotInitException {
        // Step 1 初始化保存的线程
        persistenceManager.init();

        // Step 2 初始化账户
        initAccounts();
    }


    /**
     * 初始化账号方法
     */
    private void initAccounts() throws DepinBotInitException {
        try {
            // Step 1 获取持久化的
            Map<Integer, AccountContext> accountContextMap = persistenceManager.loadAccountContexts();

            // Step 2 没有保存的数据，加载新的
            List<AccountContext> accountContexts;
            if (accountContextMap == null || accountContextMap.isEmpty()) {
                log.info("bot[{}]加载新账户数据", getBaseDepinBotConfig().getName());
                // Step 2.1 加载新的
                accountContexts = loadNewAccountContexts();

                // Step 2.2 持久化
                persistenceManager.persistenceAccountContexts(accountContexts);
            } else {
                log.info("bot[{}]使用历史账户数据", getBaseDepinBotConfig().getName());
                accountContexts = new ArrayList<>(accountContextMap.values());
            }

            // Step 3 加载到bot
            registerAccountsInBot(accountContexts, AccountPersistenceManager::getAccountContextPersistencePath);

            accounts.addAll(accountContexts);
        } catch (Exception e) {
            throw new DepinBotInitException("初始化账户发生错误", e);
        }
    }

    /**
     * 将账户加载到bot， 会注册监听，当属性发生改变时自动刷入磁盘
     *
     * @param accountContexts accountContexts
     */
    private void registerAccountsInBot(List<AccountContext> accountContexts, Function<AccountContext, String> getSavePath) {
        persistenceManager.registerPersistenceListener(accountContexts, getSavePath);
    }


    /**
     * 加载新的账户上下文列表，从配置文件中
     *
     * @return List<AccountContext>
     */
    private List<AccountContext> loadNewAccountContexts() {
        // Step 1 初始化账号

        List<AccountContext> newAccountContexts = new ArrayList<>();

        List<AccountContext> noProxyIds = new ArrayList<>();
        List<AccountContext> noBrowserEnvIds = new ArrayList<>();

        getAccountPool()
                .getAllItem()
                .forEach(depinClientAccount -> {
                    AccountContext accountContext = AccountContext.builder()
                            .clientAccount(depinClientAccount).build();

                    Integer id = depinClientAccount.getId();

                    // 账号没有配置代
                    if (depinClientAccount.getProxyId() == null) {
                        noProxyIds.add(accountContext);
                    } else {
                        accountContext.setProxy(getProxyPool().getItem(depinClientAccount.getProxyId()));
                    }

                    // 账号没有配置浏览器环境
                    if (depinClientAccount.getBrowserEnvId() == null) {
                        noBrowserEnvIds.add(accountContext);
                    } else {
                        accountContext.setBrowserEnv(getBrowserEnvPool().getItem(depinClientAccount.getBrowserEnvId()));
                    }

                    newAccountContexts.add(accountContext);
                });

        // Step 2 账号没代理的尝试给他设置代理
        if (!noProxyIds.isEmpty()) {
            log.warn("以下账号没有配置代理，将随机选择一个代理进行使用");
            List<NetworkProxy> lessUsedProxy = getProxyPool().getLessUsedItem(noProxyIds.size());
            for (int i = 0; i < noProxyIds.size(); i++) {
                AccountContext accountContext = noProxyIds.get(i);

                NetworkProxy proxy = lessUsedProxy.get(i);
                accountContext.setProxy(proxy);

                log.warn("账号:{},将使用代理:{}", accountContext.getName(), proxy);
            }
        }

        // Step 3 账号没浏览器环境的尝试给他设置浏览器环境
        if (!noBrowserEnvIds.isEmpty()) {
            log.warn("以下账号没有配置浏览器环境，将随机选择一个浏览器环境使用");
            List<BrowserEnv> lessUsedBrowserEnv = getBrowserEnvPool().getLessUsedItem(noBrowserEnvIds.size());
            for (int i = 0; i < noBrowserEnvIds.size(); i++) {
                AccountContext accountContext = noBrowserEnvIds.get(i);

                BrowserEnv browserEnv = lessUsedBrowserEnv.get(i);
                accountContext.setBrowserEnv(browserEnv);

                log.warn("账号:{},将使用浏览器环境:{}", accountContext.getName(), browserEnv);
            }
        }

        return newAccountContexts;
    }


    /**
     * 所有账户建立连接
     *
     * @return CompletableFuture<Void>
     */
    protected CompletableFuture<Void> connectAllAccount() {
        return CompletableFuture.runAsync(() -> {
            //Step 1 遍历账户
            List<CompletableFuture<Void>> connectFutures = accounts.stream()
                    .map(accountContext -> {
                        // Step 2 根据账户获取ws client
                        BaseDepinWSClient<Req, Resp> depinWSClient = accountWSClientMap.compute(accountContext, (k, v) -> {
                            // 没有创建过，或被关闭，创建新的
                            if (v == null || v.getClientStatus().equals(WebsocketClientStatus.SHUTDOWN)) {
                                v = buildAccountWSClient(accountContext);
                            }

                            return v;
                        });

                        String accountName = accountContext.getClientAccount().getName();

                        //Step 3 建立连接
                        WebsocketClientStatus clientStatus = depinWSClient.getClientStatus();
                        return switch (clientStatus) {
                            case NEW, STOP:  // 新创建，停止状态，需要建立连接
                                yield depinWSClient
                                        .connect()
                                        .thenAcceptAsync(success -> {
                                            try {
                                                whenAccountConnected(depinWSClient, success);
                                            } catch (Exception e) {
                                                log.error("账户[{}]-连接完成后执行回调发生错误", accountName, e);
                                            }
                                        }, executorService)
                                        .exceptionallyAsync(throwable -> {
                                            log.error("账户[{}]连接失败, ", accountName,
                                                    throwable);
                                            return null;
                                        }, executorService);
                            case STARTING, RUNNING: // 正在建立连接，直接返回
                                CompletableFuture.completedFuture(null);
                            case SHUTDOWN: // 被禁止使用，抛出异常
                                throw new DepinBotStatusException("cannot start ws client when it shutdown, " + accountName);
                        };
                    })
                    .toList();

            //Step 4 等所有账户连接建立完成
            try {
                CompletableFuture
                        .allOf(connectFutures.toArray(new CompletableFuture[0]))
                        .get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("账户建立连接发生异常", e);
            }
        }, executorService);
    }


    /**
     * 打印账号列表
     *
     * @return String
     */
    public String printAccountList() {
        List<AccountPrintDto> list = accounts.stream().map(accountContext -> {
            NetworkProxy proxy = accountContext.getProxy();
            BrowserEnv browserEnv = accountContext.getBrowserEnv();
            return AccountPrintDto
                    .builder()
                    .id(accountContext.getClientAccount().getId())
                    .name(accountContext.getName())
                    .proxyInfo(proxy.getId() + "-" + proxy.getAddress())
                    .browserEnvInfo(String.valueOf(browserEnv == null ? "NO_ENV" : browserEnv.getId()))
                    .signUp(accountContext.getClientAccount().getSignUp())
                    .startDateTime(accountContext.getConnectStatusInfo().getStartDateTime())
                    .updateDateTime(accountContext.getConnectStatusInfo().getUpdateDateTime())
                    .heartBeatCount(accountContext.getConnectStatusInfo().getHeartBeatCount().get())
                    .connectStatus(accountContext.getConnectStatusInfo().getConnectStatus())
                    .build();
        }).toList();

        return "账号列表:\n" +
                CommandLineTablePrintHelper.generateTableString(list, AccountPrintDto.class) +
                "\n";
    }

    /**
     * 打印账号收益
     *
     * @return String
     */
    public String printAccountReward() {
        StringBuilder sb = new StringBuilder();

        List<RewordInfo> list = accounts.stream().map(AccountContext::getRewordInfo).toList();

        sb.append("收益列表:\n")
                .append(CommandLineTablePrintHelper.generateTableString(list, RewordInfo.class))
                .append("\n");

        return sb.toString();
    }

}
package cn.com.helei.DepinBot.core.bot;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.commandMenu.CommandMenuNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Stack;


/**
 * 命令行交互的depin机器人
 */
@Slf4j
@Getter
public abstract class CommandLineDepinBot<Req, Resp> extends AccountAutoManageDepinBot<Req, Resp> {


    public CommandLineDepinBot(BaseDepinBotConfig baseDepinBotConfig) {
        super(baseDepinBotConfig);
    }

    /**
     * 构建command菜单
     *
     */
    protected abstract void buildMenuNode(CommandMenuNode mainManu);


    /**
     * 运行机器人
     *
     * @throws IOException IOException
     */
    protected void doExecute() throws IOException {
        //Step 1 获取输入
        CommandMenuNode mainMenuNode = getMenuNode();
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).parser(new DefaultParser()).build();

        Stack<CommandMenuNode> menuNodeStack = new Stack<>();
        CommandMenuNode currentMenuNode = mainMenuNode;

        //Step 2 不断监听控制台输入
        while (true) {
            boolean inputAccept = true;

            //Step 2.1 获取输入
            String choice = reader.readLine("\n<\n" + getInvokeActionAndMenuNodePrintStr(currentMenuNode) + "请选择>").trim();
            try {
                //Step 2.2 退出
                if ("exit".equals(choice)) {
                    exitHandler();
                    break;
                }

                //Step 2.3 选择操作
                int option = Integer.parseInt(choice.trim());
                if (option == 0) {
                    //返回上一级菜单
                    if (!menuNodeStack.isEmpty()) {
                        currentMenuNode = menuNodeStack.pop();
                    }
                } else if (option > 0 && option <= currentMenuNode.getSubNodeList().size()) {
                    //进入选择的菜单
                    menuNodeStack.push(currentMenuNode);
                    currentMenuNode = currentMenuNode.getSubNodeList().get(option - 1);
                } else {
                    inputAccept = false;
                }

                //终点节点，不进入，直接返回
                if (currentMenuNode.isEnd()) {
                    System.out.println(getInvokeActionAndMenuNodePrintStr(currentMenuNode));
                    currentMenuNode = menuNodeStack.pop();
                }
            } catch (Exception e) {
                inputAccept = false;
            }

            try {
                if (!inputAccept && currentMenuNode.getResolveInput() != null) {
                    currentMenuNode.getResolveInput().accept(choice);
                }
            } catch (Exception e) {
                System.out.println("系统异常");
            }

        }
    }


    /**
     * 获取菜单， 会放入额外的固定菜单
     *
     * @return CommandMenuNode
     */
    private CommandMenuNode getMenuNode() {
        CommandMenuNode mainManu = new CommandMenuNode(
                "主菜单",
                String.format("欢迎使用[%s]-bot", getBaseDepinBotConfig().getName()),
                this::printBanner
        );

       buildMenuNode(mainManu);

       return mainManu;
    }

    private String printBanner() {
        return "";
    }

    /**
     * 退出回调
     */
    protected void exitHandler() {
    }



    /**
     * 执行Action回调，获取当前菜单打印的字符串
     *
     * @param currentMenuNode currentMenuNode
     * @return String
     */
    public String getInvokeActionAndMenuNodePrintStr(CommandMenuNode currentMenuNode) {
        StringBuilder sb = new StringBuilder();
        sb.append(currentMenuNode.getDescribe()).append("\n");

        if (currentMenuNode.getAction() != null) {
            sb.append(currentMenuNode.getAction().get()).append("\n");
        }

        if (currentMenuNode.isEnd()) return sb.toString();

        sb.append("选项:\n");
        List<CommandMenuNode> menuNodeList = currentMenuNode.getSubNodeList();
        for (int i = 0; i < menuNodeList.size(); i++) {
            sb.append(i + 1).append(". ").append(menuNodeList.get(i).getTittle()).append("\n");
        }

        sb.append("0. 返回上一级菜单\n");

        return sb.toString();
    }

}
package cn.com.helei.DepinBot.core.bot;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.BaseDepinWSClient;
import cn.com.helei.DepinBot.core.commandMenu.CommandMenuNode;
import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;


@Slf4j
public abstract class DefaultMenuCMDLineDepinBot<C extends BaseDepinBotConfig> extends CommandLineDepinBot<JSONObject, JSONObject> {


    private static final String INVITE_CODE_KEY = "inviteCode";

    /**
     * 刷新节点
     */
    public static final CommandMenuNode REFRESH_NODE = new CommandMenuNode(true, "刷新", "当前数据已刷新", null);

    /**
     * 是否开始过链接所有账号
     */
    private final AtomicBoolean isStartAccountConnected = new AtomicBoolean(false);

    private final C botConfig;

    public DefaultMenuCMDLineDepinBot(C botConfig) {
        super(botConfig);

        this.botConfig = botConfig;
    }


    @Override
    protected void buildMenuNode(CommandMenuNode mainManu) {
        mainManu.addSubMenu(buildRegisterMenuNode())
                .addSubMenu(buildQueryTokenMenuNode())
                .addSubMenu(buildProxyListMenuNode())
                .addSubMenu(buildBrowserListMenuNode())
                .addSubMenu(buildAccountListMenuNode())
                .addSubMenu(buildStartAccountConnectMenuNode());
    }


    /**
     * 构建注册菜单节点
     *
     * @return CommandMenuNode
     */
    private CommandMenuNode buildRegisterMenuNode() {
        CommandMenuNode registerMenu = new CommandMenuNode("注册",
                "请确认邀请码后运行", this::printCurrentInvite);

        CommandMenuNode interInvite = new CommandMenuNode(
                "填入邀请码",
                "请输入邀请码：",
                this::printCurrentInvite
        );
        interInvite.setResolveInput(input -> {
            log.info("邀请码修改[{}]->[{}]", botConfig.getConfig(INVITE_CODE_KEY), input);
            botConfig.setConfig(INVITE_CODE_KEY, input);
        });


        return registerMenu.addSubMenu(interInvite)
                .addSubMenu(new CommandMenuNode(
                        "开始注册",
                        "开始注册所有账号...",
                        this::registerAllAccount
                ));
    }

    /**
     * 获取token
     *
     * @return CommandMenuNode
     */
    private CommandMenuNode buildQueryTokenMenuNode() {
        return new CommandMenuNode("获取token", "开始获取所有账号token...", this::loadAllAccountToken);
    }

    /**
     * 构建查看代理列表的菜单节点
     *
     * @return 查看代理列表菜单节点
     */
    private CommandMenuNode buildProxyListMenuNode() {
        return new CommandMenuNode(
                "查看代理列表",
                "当前代理列表文件:" + getProxyPool().getConfigClassPath(),
                getProxyPool()::printPool
        ).addSubMenu(REFRESH_NODE);
    }

    /**
     * 构建查看浏览器环境列表的菜单节点
     *
     * @return 查看浏览器环境列表菜单节点
     */
    private CommandMenuNode buildBrowserListMenuNode() {
        return new CommandMenuNode(
                "查看浏览器环境列表",
                "当前代理列表文件:" + getBrowserEnvPool().getConfigClassPath(),
                getBrowserEnvPool()::printPool
        ).addSubMenu(REFRESH_NODE);
    }

    /**
     * 账户列表菜单节点
     *
     * @return 账户列表节点
     */
    private CommandMenuNode buildAccountListMenuNode() {
        CommandMenuNode accountListMenuNode = new CommandMenuNode(
                "查看账号",
                "当前账户详情列表:",
                this::printAccountList
        );

        return accountListMenuNode
                .addSubMenu(buildAccountRewardMenuNode())
                .addSubMenu(REFRESH_NODE);
    }

    /**
     * 查看账户收益菜单节点
     *
     * @return 账户收益节点
     */
    private CommandMenuNode buildAccountRewardMenuNode() {
        return new CommandMenuNode(
                "查看账号收益",
                "账号收益详情列表:",
                this::printAccountReward
        ).addSubMenu(REFRESH_NODE);
    }

    /**
     * 开始账户连接菜单节点
     *
     * @return 连接账户菜单节点
     */
    private CommandMenuNode buildStartAccountConnectMenuNode() {
        CommandMenuNode menuNode = new CommandMenuNode(
                "启动账号",
                "启动账号界面，",
                this::startAccountsDepin
        );

        CommandMenuNode refresh = new CommandMenuNode(true, "刷新", "当前账户列表",
                this::printAccountList);

        menuNode.addSubMenu(refresh);
        return menuNode;
    }

    /**
     * 注册所有账号
     *
     * @return String
     */
    private String registerAllAccount() {
        CompletableFuture.supplyAsync(() -> {
            List<CompletableFuture<Boolean>> futures = getAccounts().stream()
                    .map(account -> {
//                        if (true) return CompletableFuture.completedFuture(true);

                        // 账户注册过，
                        if (BooleanUtil.isTrue(account.getClientAccount().getSignUp())) {
                            log.warn("账户[{}]-email[{}]注册过", account.getName(), account.getClientAccount().getEmail());
                            return CompletableFuture.completedFuture(false);
                        } else {
                            return registerAccount(account, botConfig.getConfig(INVITE_CODE_KEY));
                        }
                    }).toList();

            int successCount = 0;
            for (int i = 0; i < futures.size(); i++) {
                CompletableFuture<Boolean> future = futures.get(i);
                AccountContext accountContext = getAccounts().get(i);
                try {
                    if (future.get()) {
                        //注册成功
                        successCount++;
                        accountContext.getClientAccount().setSignUp(true);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    log.error("注册账号[{}]发生错误, {}", accountContext.getName(), e.getMessage());
                }
            }

            return String.format("所有账号注册完毕，[%d/%d]", successCount, getAccounts().size());
        }, getExecutorService());

        return "已开始账户注册";
    }

    /**
     * 获取账号的token
     *
     * @return String
     */
    public String loadAllAccountToken() {
        List<CompletableFuture<String>> futures = getAccounts().stream()
                .map(this::requestTokenOfAccount).toList();

        int successCount = 0;
        for (int i = 0; i < futures.size(); i++) {
            CompletableFuture<String> future = futures.get(i);
            AccountContext accountContext = getAccounts().get(i);
            try {
                String token = future.get();
                if (StrUtil.isNotBlank(token)) {
                    successCount++;
                    accountContext.getParams().put("token", token);
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("账号[{}]获取token发生错误, {}", accountContext.getName(), e.getMessage());
            }
        }

        return String.format("所有账号获取token完毕，[%d/%d]", successCount, getAccounts().size());
    }


    /**
     * 开始所有账户的连接
     *
     * @return String 打印的消息
     */
    private String startAccountsDepin() {
        if (isStartAccountConnected.compareAndSet(false, true)) {
            connectAllAccount()
                    .exceptionally(throwable -> {
                        log.error("开始所有账户连接时发生异常", throwable);
                        return null;
                    });
            return "已开始账号链接任务";
        }

        return "已提交过建立连接任务";
    }

    /**
     * 打印当前的邀请码
     *
     * @return 邀请码
     */
    private String printCurrentInvite() {
        String inviteCode = botConfig.getConfigMap().get("inviteCode");
        return "(当前邀请码为:" + inviteCode + ")";
    }


    /**
     * 注册账户
     *
     * @param accountContext accountContext
     * @param inviteCode     inviteCode
     * @return CompletableFuture<Boolean> 是否注册成功
     */
    protected abstract CompletableFuture<Boolean> registerAccount(AccountContext accountContext, String inviteCode);

    /**
     * 请求获取账户token
     *
     * @param accountContext accountContext
     * @return CompletableFuture<String> token
     */
    protected abstract CompletableFuture<String> requestTokenOfAccount(AccountContext accountContext);

    /**
     * 连接所有账户
     *
     * @return CompletableFuture<Void>
     */
    protected abstract CompletableFuture<Void> connectAllAccount();


    @Override
    public BaseDepinWSClient<JSONObject, JSONObject> buildAccountWSClient(AccountContext accountContext) {
        return null;
    }

    @Override
    public void whenAccountConnected(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, Boolean success) {

    }

    @Override
    public void whenAccountReceiveResponse(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, String id, JSONObject response) {

    }

    @Override
    public void whenAccountReceiveMessage(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, JSONObject message) {

    }

    @Override
    public JSONObject getHeartbeatMessage(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient) {
        return null;
    }
}
package cn.com.helei.DepinBot.core.commandMenu;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Data
public class CommandMenuNode {

    private final boolean isEnd;

    /**
     * 当前节点被选择时显示的文本
     */
    private String tittle;

    /**
     * 当前节点进入后的describe
     */
    private String describe;

    /**
     * 当前节点调用的函数, 返回内容会显示在describe 和 子节点选项之间
     */
    private Supplier<String> action;

    /**
     * 处理输入
     */
    private Consumer<String> resolveInput;

    /**
     * 当前节点的子节点
     */
    private final List<CommandMenuNode> subNodeList = new ArrayList<>();

    public CommandMenuNode(String tittle, String describe, Supplier<String> action) {
        this(false, tittle, describe, action);
    }

    public CommandMenuNode(boolean isEnd, String tittle, String describe, Supplier<String> action) {
        this.isEnd = isEnd;
        this.tittle = tittle;
        this.describe = describe;
        this.action = action;
    }

    public CommandMenuNode addSubMenu(CommandMenuNode subMenu) {
        this.subNodeList.add(subMenu);
        return this;
    }
}
package cn.com.helei.DepinBot.core.dto.account;

import cn.com.helei.DepinBot.core.supporter.propertylisten.PropertyChangeListenClass;
import cn.com.helei.DepinBot.core.supporter.propertylisten.PropertyChangeListenField;
import cn.com.helei.DepinBot.core.pool.account.DepinClientAccount;
import cn.com.helei.DepinBot.core.pool.env.BrowserEnv;
import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import cn.com.helei.DepinBot.core.dto.RewordInfo;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PropertyChangeListenClass(isDeep = true)
public class AccountContext {

    /**
     * 账户是否可用
     */
    @PropertyChangeListenField
    private boolean usable = true;

    /**
     * client 账户
     */
    @PropertyChangeListenField
    private DepinClientAccount clientAccount;

    /**
     * 代理
     */
    @PropertyChangeListenField
    private NetworkProxy proxy;

    /**
     * 浏览器环境
     */
    @PropertyChangeListenField
    private BrowserEnv browserEnv;

    private LocalDateTime saveDatetime;


    /**
     * 连接状态
     */
    private final ConnectStatusInfo connectStatusInfo = new ConnectStatusInfo();

    /**
     * 分数信息
     */
    @PropertyChangeListenField
    private final RewordInfo rewordInfo = new RewordInfo();

    @PropertyChangeListenField
    private final Map<String, String> params = new HashMap<>();

    public String getParam(String key) {
        return params.get(key);
    }

    public void setParam(String key, String value) {
        params.put(key, value);
    }

    public Map<String, String> getWSHeaders() {
        return clientAccount.getWSHeaders();
    }

    public Map<String, String> getRestHeaders() {
        return clientAccount.getRestHeaders();
    }

    public String getName() {
        return clientAccount.getName() == null ? clientAccount.getEmail() : clientAccount.getName();
    }
}
package cn.com.helei.DepinBot.core.dto.account;

import cn.com.helei.DepinBot.core.constants.ConnectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPrintDto {

    private Integer id;

    private String name;

    private String proxyInfo;

    private String browserEnvInfo;

    private Boolean signUp;

    private LocalDateTime startDateTime;

    private LocalDateTime updateDateTime;

    private Integer heartBeatCount;

    private ConnectStatus connectStatus;
}
package cn.com.helei.DepinBot.core.pool.account;


import cn.com.helei.DepinBot.core.supporter.propertylisten.PropertyChangeListenClass;
import cn.com.helei.DepinBot.core.supporter.propertylisten.PropertyChangeListenField;
import cn.com.helei.DepinBot.core.pool.AbstractYamlLineItem;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PropertyChangeListenClass
public class DepinClientAccount extends AbstractYamlLineItem {

    /**
     * 账户名
     */
    @PropertyChangeListenField
    private String name;

    /**
     * 邮箱
     */
    @PropertyChangeListenField
    private String email;

    /**
     * 密码
     */
    @PropertyChangeListenField
    private String password;

    /**
     * 是否注册过
     */
    @PropertyChangeListenField
    private Boolean signUp;

    /**
     * 代理id
     */
    @PropertyChangeListenField
    private Integer proxyId;


    /**
     * 浏览器环境id
     */
    @PropertyChangeListenField
    private Integer browserEnvId;


    public DepinClientAccount(Object originLine) {
        String emailAndPassword = (String) originLine;

        String[] split = emailAndPassword.split(", ");
        email = split[0];

        String[] emailSplit = email.split("@");
        if (emailSplit.length != 2) {
            throw new IllegalArgumentException("邮箱格式错误");
        }
        this.name = emailSplit[0];


        password = split[1];

        if (split.length == 3) {
            this.proxyId = Integer.valueOf(split[2]);
        }
        if (split.length == 4) {
            this.browserEnvId = Integer.valueOf(split[2]);
        }
    }

    public Map<String, String> getWSHeaders() {
        return new HashMap<>();
    }

    public Map<String, String> getRestHeaders() {
        return new HashMap<>();
    }

    public String getConnectUrl() {
        return "";
    }
}
package cn.com.helei.DepinBot.core.pool.env;

import cn.com.helei.DepinBot.core.pool.AbstractYamlLineItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BrowserEnv extends AbstractYamlLineItem {

    private Map<String, String> headers;

    public BrowserEnv(Object originLine) {
        Map<String, String> map = (Map<String, String>) originLine;
        if (map != null) {
            headers = new HashMap<>(map);
        }
    }
}
package cn.com.helei.DepinBot.core.supporter.persistence;

import cn.com.helei.DepinBot.core.util.DiscardingBlockingQueue;
import cn.com.helei.DepinBot.core.util.FileUtil;
import cn.com.helei.DepinBot.core.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class DumpDataSupporter {

    private final ConcurrentMap<String, DiscardingBlockingQueue<String>> updateQueueDumpPathMap = new ConcurrentHashMap<>();

    /**
     * 执行的线程池
     */
    private final ExecutorService executorService;

    private final AtomicBoolean running = new AtomicBoolean(false);

    private final long intervalSecond = 10;

    public DumpDataSupporter() {
        this.executorService = Executors.newThreadPerTaskExecutor(new NamedThreadFactory("persistence-"));
    }


    /**
     * 绑定更新队列
     *
     * @param dumpPath dumpPath
     * @param queue    queue
     */
    public void bindUpdateQueue(String dumpPath, DiscardingBlockingQueue<String> queue) {
        updateQueueDumpPathMap.compute(dumpPath, (k, v) -> {
            if (v == null) {
                v = queue;
            }

            return v;
        });
    }

    /**
     * 开启dump任务
     */
    public void startDumpTask() {
        if (running.compareAndSet(false, true)) {
            executorService.execute(() -> {
                while (running.get()) {
                    try {
                        TimeUnit.SECONDS.sleep(intervalSecond);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        Integer successCount = dumpAllQueue().get();
                        log.debug("dump执行完毕，成功[{}],共[{}]", successCount, updateQueueDumpPathMap.size());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            log.warn("dump 线程已启动，无需程序开启");
        }
    }


    /**
     * dumpAllQueue
     *
     * @return success count
     */
    private CompletableFuture<Integer> dumpAllQueue() {
        log.debug("开始启动dump任务");

        List<CompletableFuture<Boolean>> futures = updateQueueDumpPathMap
                .entrySet()
                .stream()
                .map(e ->
                        dumpQueue(e.getKey(), e.getValue())
                                .exceptionallyAsync(throwable -> {
                                    log.error("保存[{}]发生异常", e.getKey(), throwable);
                                    return null;
                                }, executorService)
                )
                .toList();

        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenApplyAsync(unused -> {
                    int count = 0;
                    for (CompletableFuture<Boolean> future : futures) {
                        try {
                            if (future.get()) {
                                count++;
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return count;
                }, executorService);
    }


    /**
     * dump
     *
     * @param dumpPath dumpPath
     * @param queue    queue
     * @return CompletableFuture<Boolean>
     */
    public CompletableFuture<Boolean> dumpQueue(String dumpPath, DiscardingBlockingQueue<String> queue) {
        return CompletableFuture.supplyAsync(() -> {
            String dump = null;

            // 循环取到最新的dump数据
            while (!queue.isEmpty()) {
                try {
                    dump = queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException("take data from DiscardingBlockingQueue error", e);
                }
            }

            if (dump != null) {
                // 保存
                try {
                    FileUtil.saveJSONStringContext(Path.of(dumpPath), dump);
                    return true;
                } catch (Exception e) {
                    log.error("保存[{}]发生异常", dumpPath, e);
                }
            }

            return false;
        }, executorService);
    }
}
package cn.com.helei.DepinBot.core.supporter.propertylisten;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyChangeProxy implements MethodInterceptor {

    private final Object target;

    private final PropertyChangeListener listener;

    private final Map<String, Object> fieldValues = new HashMap<>();

    public PropertyChangeProxy(Object target, PropertyChangeListener listener) {
        this.target = target;
        this.listener = listener;
        Class<?> targetClass = target.getClass();

        if (targetClass.isAnnotationPresent(PropertyChangeListenClass.class) || target instanceof Map<?, ?>) {
            List<Field> fields = new ArrayList<>();
            fields.addAll(List.of(targetClass.getDeclaredFields()));
            fields.addAll(List.of(targetClass.getSuperclass().getDeclaredFields()));

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(PropertyChangeListenField.class)) {
                    String name = getFieldName(field);
                    try {
                        Object fieldValue = field.get(target);

                        if (fieldValue instanceof Map<?, ?>) {
                            fieldValue = createMapProxy((Map<?, ?>) fieldValue, name);
                            field.set(target, fieldValue);
                        }

                        fieldValues.put(name, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("无法访问字段值", e);
                    }
                }
            }
        }
    }

    public static <T> T createProxy(T target, PropertyChangeListener listener) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new PropertyChangeProxy(target, listener));

        return (T) enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object invoke;
        String fieldName = getFieldNameFromMethod(method);

        if (isSetter(method) && fieldValues.containsKey(fieldName)) {

            // 是代理的目标
            invoke = methodProxy.invoke(target, args);


            Object oldValue = fieldValues.get(fieldName);
            Object newValue = args[0];

            if (newValue instanceof Map<?, ?>) {
                newValue = createMapProxy((Map<?, ?>) newValue, fieldName);
            }

            //属性值发生变化
            if ((oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue))) {
                fieldValues.put(fieldName, newValue);
                listener.onPropertyChanged(new PropertyChangeInvocation(
                        target,
                        fieldName,
                        oldValue,
                        newValue,
                        System.currentTimeMillis())
                );
            }
        } else {
            invoke = method.invoke(target, args);
        }

        return invoke;
    }


    private Map<?, ?> createMapProxy(Map<?, ?> originalMap, String fieldName) {
        Object o = Enhancer.create(Map.class, (MethodInterceptor) (obj, method, args, methodProxy) -> {
            PropertyChangeInvocation invocation = null;

            if (method.getName().equals("put")) {
                Object key = args[0];
                Object value = args[1];

                invocation = new PropertyChangeInvocation(target,
                        fieldName + "[" + key + "]", originalMap.get(key), value, System.currentTimeMillis());
            }

            if (method.getName().equals("remove")) {
                Object key = args[0];

                invocation = new PropertyChangeInvocation(target,
                        fieldName + "[" + key + "]", originalMap.get(key), null, System.currentTimeMillis());
            }

            Object invoke = method.invoke(originalMap, args);

            if (invocation != null) {
                listener.onPropertyChanged(invocation);
            }

            return invoke;
        });

        return (Map<?, ?>) o;
    }

    private String getFieldNameFromMethod(Method method) {
        String name = method.getName().substring(3);

        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }


    private boolean isSetter(Method method) {
        return method.getName().startsWith("set") && method.getParameters().length == 1;
    }

    private static @NotNull String getFieldName(Field field) {
        PropertyChangeListenField persistenceField = field.getAnnotation(PropertyChangeListenField.class);
        String name = persistenceField.name();
        if (name.isEmpty()) {
            name = field.getName();
        }
        return name;
    }

}
package cn.com.helei.DepinBot.core.util;

import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;


@Slf4j
public class RestApiClient {

    private static final int RETRY_TIMES = 1;

    private final OkHttpClient okHttpClient;

    private final ExecutorService executorService;

    public RestApiClient(
            NetworkProxy proxy,
            ExecutorService executorService
    ) {
        this.executorService = executorService;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (proxy != null) {
            builder.proxy(new Proxy(Proxy.Type.HTTP, proxy.getAddress()))
                    .proxyAuthenticator((route, response) -> {
                        String credential = Credentials.basic(proxy.getUsername(), proxy.getPassword());
                        return response.request().newBuilder()
                                .header("Proxy-Authorization", credential)
                                .build();
                    })
            ;
        }
        this.okHttpClient = builder.build();
    }


    /**
     * 发送请求，如果有asKey参数不为null，则会鉴权
     *
     * @param url     url
     * @param method  method
     * @param headers headers
     * @param params  params
     * @param body    body
     * @return CompletableFuture<JSONObject>
     */
    public CompletableFuture<String> request(
            String url,
            String method,
            Map<String, String> headers,
            JSONObject params,
            JSONObject body
    ) {
        return CompletableFuture.supplyAsync(() -> {
            // 创建表单数据
            StringBuilder queryString = new StringBuilder();

            String requestUrl = url;
            if (params != null) {
                params.keySet().forEach(key -> {
                    queryString.append(key).append("=").append(params.get(key)).append("&");
                });

                if (!queryString.isEmpty()) {
                    queryString.deleteCharAt(queryString.length() - 1);
                }
                requestUrl = url + "?" + queryString;
            }


            Request.Builder builder = new Request.Builder();

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    builder.addHeader(header.getKey(), header.getValue());
                }
            }
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            RequestBody requestBody = null;
            if (body != null) {
                requestBody  = RequestBody.create(body.toJSONString(), JSON);
            }


            // 创建 POST 请求
            builder.url(requestUrl);
            String upperCase = method.toUpperCase();
            if (upperCase.equals("GET")) {
                builder.get();
            } else {
                builder.method(upperCase, requestBody);
            }

            Request request = builder.build();

            log.debug("创建请求 url[{}], method[{}]成功，开始请求服务器", url, method);

            String bodyMsg = null;
            for (int i = 0; i < RETRY_TIMES; i++) {

                // 发送请求并获取响应
                try (Response response = okHttpClient.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        return response.body() == null ? "{}" : response.body().string();
                    } else {

                        bodyMsg = response.body() != null ? response.body().string() : null;
                        log.warn("请求url [{}] 失败， code [{}]， {}",
                                url, response.code(), bodyMsg);
                        break;
                    }
                } catch (SocketTimeoutException e) {
                    throw new RuntimeException(String.format("请求[%s]超时，尝试重新请求 [%s/%s],", url, i, RETRY_TIMES), e);
                } catch (IOException e) {
                    throw new RuntimeException("请求url [" + url + "] 失败", e);
                }
            }

            if (bodyMsg != null) {
                throw new RuntimeException("请求失败：" + bodyMsg);
            }

            return null;
        }, executorService);
    }
}
package cn.com.helei.DepinBot.core;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class BaseDepinBotConfig {

    /**
     * 名字
     */
    private String name = "Default Name";

    /**
     * 并发数量
     */
    private int concurrentCount = 5;


    /**
     * 账户奖励刷新间隔
     */
    private long accountRewardRefreshIntervalSeconds = 600;

    /**
     * 网络代理池配置文件名
     */
    private String networkPoolConfig = "bot/network-proxy.yaml";

    /**
     * 浏览器环境池配置文件名
     */
    private String browserEnvPoolConfig = "bot/browser-env.yaml";

    /**
     * 账户配置文件名
     */
    private String accountPoolConfig = "bot/account.yaml";

    /**
     * 连接url
     */
    private String wsBaseUrl;


    private Map<String, String> configMap = new HashMap<>();

    public String getConfig(String key) {
        return configMap.get(key);
    }

    public void setConfig(String key, String value) {
        this.configMap.put(key, value);
    }
}
package cn.com.helei.DepinBot.core;

import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import cn.com.helei.DepinBot.core.constants.ConnectStatus;
import cn.com.helei.DepinBot.core.netty.base.AbstractWebsocketClient;
import cn.com.helei.DepinBot.core.netty.constants.WebsocketClientStatus;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
@Getter
public abstract class BaseDepinWSClient<Req, Resp> extends AbstractWebsocketClient<Req, Resp> {


    /**
     * client对应的账号
     */
    private final AccountContext accountContext;

    public BaseDepinWSClient(
            AccountContext accountContext,
            BaseDepinWSClientHandler<Req, Resp> handler
    ) {
        super(accountContext.getClientAccount().getConnectUrl(), handler);

        DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
        accountContext.getWSHeaders().forEach(httpHeaders::add);
        super.setHeaders(httpHeaders);

        super.setName(accountContext.getClientAccount().getName());
        super.setProxy(accountContext.getProxy());
        super.setClientStatusChangeHandler(this::whenClientStatusChange);

        this.accountContext = accountContext;

        updateClientStatus(WebsocketClientStatus.NEW);
    }


    public abstract Req getHeartbeatMessage(BaseDepinWSClient<Req, Resp> wsClient);

    public abstract void whenAccountReceiveResponse(BaseDepinWSClient<Req, Resp> wsClient, String id, Resp response) ;

    public abstract void whenAccountReceiveMessage(BaseDepinWSClient<Req, Resp> wsClient, Resp message);
    /**
     * ws客户端状态改变，同步更新账户状态
     *
     * @param newClientStatus 最新的客户端状态
     */
    public void whenClientStatusChange(WebsocketClientStatus newClientStatus) {
        accountContext.getConnectStatusInfo().setConnectStatus(
                switch (newClientStatus) {
                    case NEW -> {
                        accountContext.getConnectStatusInfo().setStartDateTime(LocalDateTime.now());
                        accountContext.getConnectStatusInfo().setUpdateDateTime(LocalDateTime.now());
                        yield ConnectStatus.NEW;
                    }
                    case STARTING -> {
                        accountContext.getConnectStatusInfo().setUpdateDateTime(LocalDateTime.now());
                        yield ConnectStatus.STARTING;
                    }
                    case RUNNING -> {
                        accountContext.getConnectStatusInfo().setUpdateDateTime(LocalDateTime.now());
                        yield ConnectStatus.RUNNING;
                    }
                    case STOP, SHUTDOWN -> {
                        accountContext.getConnectStatusInfo().setUpdateDateTime(LocalDateTime.now());
                        accountContext.setUsable(false);
                        yield ConnectStatus.STOP;
                    }
                }
        );
    }
}
package cn.com.helei.DepinBot.depined;

import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import cn.com.helei.DepinBot.core.pool.account.DepinClientAccount;
import cn.com.helei.DepinBot.core.pool.env.BrowserEnv;
import cn.com.helei.DepinBot.core.exception.RegisterException;
import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import cn.com.helei.DepinBot.core.util.RestApiClient;
import cn.com.helei.DepinBot.core.util.RestApiClientFactory;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DepinedApi {

//    private DConfi

    public CompletableFuture<Void> register(NetworkProxy proxy, AccountContext accountContext) {
        DepinClientAccount clientAccount = accountContext.getClientAccount();
        JSONObject body = new JSONObject();
        body.put("email", clientAccount.getEmail());
        body.put("password", clientAccount.getPassword());

        BrowserEnv browserEnv = accountContext.getBrowserEnv();


        Map<String, String> headers = new HashMap<>(browserEnv.getHeaders());
        headers.put("content-type", "application/json");
        headers.put("origin", "https://app.depined.org");
        headers.put("referer", "https://app.depined.org/");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");


        return getRestApiClient(proxy)
                .request("https://api.depined.org/api/user/register", "POST", headers, null, body)
                .thenAcceptAsync(res -> {
                    // 注册获得token
                    JSONObject result = JSONObject.parseObject(res);

                    if (result.getBoolean("status")) {
                        JSONObject data = result.getJSONObject("data");
                        accountContext.setParam("token", data.getString("token"));
                        accountContext.setParam("user_id", data.getString("user_id"));

                    } else {
                        throw new RegisterException(String.format("账户[%s]注册失败，[%s]", accountContext.getName(), res));
                    }
                }).thenRunAsync(() -> {
                    //设置用户名
                    try {
                        setUsername(proxy, accountContext).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RegisterException(String.format("账户[%s]注册失败，[%s]", accountContext.getName(), e.getMessage()));
                    }
                }).thenRunAsync(()->{
                    //设置Rendering


                });

    }


    public CompletableFuture<Void> setUsername(NetworkProxy proxy, AccountContext accountContext) {
        String token = accountContext.getParam("token");
        String username = accountContext.getClientAccount().getEmail().split("@")[0];

        JSONObject body = new JSONObject();

        body.put("username", username);
        body.put("step", "username");

        BrowserEnv browserEnv = accountContext.getBrowserEnv();
        Map<String, String> headers = new HashMap<>(browserEnv.getHeaders());

        headers.put("content-type", "application/json");
        headers.put("origin", "https://app.depined.org");
        headers.put("referer", "https://app.depined.org/");
        headers.put("authorization", "Bearer " + token);

        return getRestApiClient(proxy)
                .request("https://api.depined.org/api/user/profile-creation", "POST", headers, null, body)
                .thenAcceptAsync(res -> {
                    // 注册获得token
                    JSONObject result = JSONObject.parseObject(res);

                    if (!result.getBoolean("status")) {
                        throw new RegisterException(String.format("账户[%s]设置用户名失败，[%s]", accountContext.getName(), res));
                    }
                });
    }

    public CompletableFuture<Void> setRendering(NetworkProxy proxy, AccountContext accountContext) {
        String token = accountContext.getParam("token");
        JSONObject body = new JSONObject();

        body.put("step", "description");
        body.put("description", "Rendering");

        BrowserEnv browserEnv = accountContext.getBrowserEnv();
        Map<String, String> headers = new HashMap<>(browserEnv.getHeaders());

        headers.put("content-type", "application/json");
        headers.put("origin", "https://app.depined.org");
        headers.put("referer", "https://app.depined.org/");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        headers.put("authorization", "Bearer " + token);

        return getRestApiClient(proxy)
                .request("https://api.depined.org/api/user/profile-creation", "POST", headers, null, body)
                .thenAcceptAsync(res -> {
                    // 注册获得token
                    JSONObject result = JSONObject.parseObject(res);

                    if (!result.getBoolean("status")) {
                        throw new RegisterException(String.format("账户[%s]设置Rendering失败，[%s]", accountContext.getName(), res));
                    }
                });
    }

    public CompletableFuture<Void> setReferral(NetworkProxy proxy, AccountContext accountContext) {
        String token = accountContext.getParam("token");
        JSONObject body = new JSONObject();

//        body.put("referral_code", );

        BrowserEnv browserEnv = accountContext.getBrowserEnv();
        Map<String, String> headers = new HashMap<>(browserEnv.getHeaders());

        headers.put("content-type", "application/json");
        headers.put("origin", "https://app.depined.org");
        headers.put("referer", "https://app.depined.org/");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        headers.put("authorization", "Bearer " + token);

        return getRestApiClient(proxy)
                .request("https://api.depined.org/api/user/profile-creation", "POST", headers, null, body)
                .thenAcceptAsync(res -> {
                    // 注册获得token
                    JSONObject result = JSONObject.parseObject(res);

                    if (!result.getBoolean("status")) {
                        throw new RegisterException(String.format("账户[%s]设置Rendering失败，[%s]", accountContext.getName(), res));
                    }
                });
    }
    private RestApiClient getRestApiClient(NetworkProxy networkProxy) {
        return RestApiClientFactory.getClient(networkProxy);
    }

}
package cn.com.helei.DepinBot.depined;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.BaseDepinWSClient;
import cn.com.helei.DepinBot.core.bot.CommandLineDepinBot;
import cn.com.helei.DepinBot.core.commandMenu.CommandMenuNode;
import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import com.alibaba.fastjson.JSONObject;

public class DepinedDepinBot extends CommandLineDepinBot<JSONObject, JSONObject> {


    public DepinedDepinBot(BaseDepinBotConfig baseDepinBotConfig) {
        super(baseDepinBotConfig);
    }

    @Override
    protected void buildMenuNode(CommandMenuNode mainManu) {
        mainManu.addSubMenu(new CommandMenuNode("账户注册", "开始注册账户", this::registerAccountAction));
    }


    private String registerAccountAction() {
        return null;
    }


    @Override
    public BaseDepinWSClient<JSONObject, JSONObject> buildAccountWSClient(AccountContext accountContext) {
        return null;
    }

    @Override
    public void whenAccountConnected(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, Boolean success) {

    }

    @Override
    public void whenAccountReceiveResponse(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, String id, JSONObject response) {

    }

    @Override
    public void whenAccountReceiveMessage(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, JSONObject message) {

    }

    @Override
    public JSONObject getHeartbeatMessage(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient) {
        return null;
    }

}
package cn.com.helei.DepinBot.oasis;

import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import cn.com.helei.DepinBot.core.util.RestApiClient;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Slf4j
public class OasisApi {

    private static final Map<NetworkProxy, RestApiClient> proxyRestApiClientMap = new ConcurrentHashMap<>();

    private final ExecutorService executorService;

    public OasisApi(ExecutorService executorService) {
        this.executorService = executorService;
    }


    /**
     * 注册用户
     *
     * @param email      邮箱
     * @param password   密码
     * @param inviteCode 邀请码
     * @return 结果
     */
    public CompletableFuture<Boolean> registerUser(NetworkProxy networkProxy, String email, String password, String inviteCode) {

        JSONObject account = new JSONObject();
        account.put("email", email);
        account.put("password", password);
        account.put("referralCode", inviteCode);

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");

        return getRestApiClient(networkProxy)
                .request("https://api.oasis.ai/internal/auth/signup", "post", headers, null, account)
                .thenApplyAsync((res) -> {
                    if (Objects.equals(res, "")) {
                        log.info("注册邮箱[{}]成功！请前往验证", email);
                        return true;
                    } else {
                        log.error("注册[{}]失败, {}", email, res);
                        return false;
                    }
                });
    }

    /**
     * 登录用户,返回token
     *
     * @param email    邮箱
     * @param password 密码
     * @return 结果
     */
    public CompletableFuture<String> loginUser(NetworkProxy networkProxy, String email, String password) {

        JSONObject account = new JSONObject();
        account.put("email", email);
        account.put("password", password);
        account.put("rememberSession", true);

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        return getRestApiClient(networkProxy)
                .request("https://api.oasis.ai/internal/auth/login", "post", headers, null, account)
                .thenApplyAsync((res) -> {
                    JSONObject result = JSONObject.parseObject(res);
                    return result.getString("token");
                });
    }

    /**
     * 登录用户,返回token
     *
     * @param email    邮箱
     * @return 结果
     */
    public CompletableFuture<Boolean> resendCode(NetworkProxy networkProxy, String email) {

        JSONObject account = new JSONObject();
        account.put("email", email);

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        return getRestApiClient(networkProxy)
                .request("https://api.oasis.ai/internal/auth/resend-code", "post", headers, null, account)
                .thenApplyAsync((res) -> {
                    if (StrUtil.isBlank(res)) return true;
                    else {
                        throw new RuntimeException("重发验证码错误，" + res);
                    }
                });
    }


    private RestApiClient getRestApiClient(NetworkProxy networkProxy) {
        if (networkProxy == null) {
            return new RestApiClient(null, executorService);
        }
        return proxyRestApiClientMap.compute(networkProxy, (k, v) -> {
            if (v == null) {
                v = new RestApiClient(networkProxy, executorService);
            }
            return v;
        });
    }

}
package cn.com.helei.DepinBot.oasis;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.util.YamlConfigLoadUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class OasisBotConfig extends BaseDepinBotConfig {

    private String inviteCode;

    public static void main(String[] args) {
        System.out.println(loadYamlConfig("app/oasis.yaml"));
    }

    public static OasisBotConfig loadYamlConfig(String classpath) {
        return YamlConfigLoadUtil.load(classpath, List.of("depin", "app", "oasis"), OasisBotConfig.class);
    }
}
package cn.com.helei.DepinBot.oasis;

import cn.com.helei.DepinBot.core.BaseDepinWSClient;
import cn.com.helei.DepinBot.core.bot.CommandLineDepinBot;
import cn.com.helei.DepinBot.core.SimpleDepinWSClient;
import cn.com.helei.DepinBot.core.commandMenu.CommandMenuNode;
import cn.com.helei.DepinBot.core.dto.account.AccountContext;
import cn.com.helei.DepinBot.core.pool.network.NetworkProxy;
import cn.com.helei.DepinBot.core.util.SystemInfo;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OasisDepinBot extends CommandLineDepinBot<JSONObject, JSONObject> {

    private final OasisApi oasisApi;

    private final Semaphore concurrentSemaphore;

    public OasisDepinBot(String oasisBotConfigPath) {
        super(OasisBotConfig.loadYamlConfig(oasisBotConfigPath));
        this.oasisApi = new OasisApi(getExecutorService());
        this.concurrentSemaphore = new Semaphore(getBaseDepinBotConfig().getConcurrentCount());
    }

    @Override
    protected void buildMenuNode(CommandMenuNode mainManu) {

        CommandMenuNode register = new CommandMenuNode(true, "账户注册",
                "开始批量注册账号", this::registerAccount);

        CommandMenuNode takeToken = new CommandMenuNode(true, "获取token",
                "开始获取token", this::loginAndTakeToken);

        CommandMenuNode resendCode = new CommandMenuNode(true, "重发验证邮件",
                "开始重发验证邮件", this::resendCode);

        mainManu.addSubMenu(register)
                .addSubMenu(takeToken)
                .addSubMenu(resendCode);
    }


    @Override
    public BaseDepinWSClient<JSONObject, JSONObject> buildAccountWSClient(AccountContext accountContext) {
        return new SimpleDepinWSClient(this, accountContext);
    }


    @Override
    public void whenAccountConnected(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, Boolean success) {
        //Step 1 发送机器信息
        depinWSClient.sendMessage(generateRandomSystemData());

        //Step 2 主动发一次心跳
        depinWSClient.sendMessage(getHeartbeatMessage(depinWSClient));
    }

    @Override
    public void whenAccountReceiveResponse(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, String id, JSONObject response) {

    }

    @Override
    public void whenAccountReceiveMessage(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient, JSONObject message) {
        String accountName = depinWSClient.getAccountContext().getClientAccount().getName();
        log.debug("账户[{}]收到消息[{}]", accountName, message);

        switch (message.getString("type")) {
            case "serverMetrics" -> {
                log.info("账户[{}]心跳已发送, token[{}]-总运行时间[{}]秒-总积分[{}]",
                        accountName,
                        message.getString("token"),
                        message.getString("totalUptime"),
                        message.getString("creditsEarned")
                );
            }
            case "acknowledged" -> {
                log.warn("系统更新:[{}]", message);
            }
            case "error" -> {
                if (message.getJSONObject("data").getString("code").equals("invalid_token")) {
                    log.warn("账户[{}]需要发送机器信息", accountName);
                    depinWSClient.sendMessage(generateRandomSystemData());
                }
            }
            default -> {
                log.warn("账户[{}]收到位置消息[{}]", accountName, message);
            }
        }
    }

    @Override
    public JSONObject getHeartbeatMessage(BaseDepinWSClient<JSONObject, JSONObject> depinWSClient) {
        log.info("账户[{}]发送心跳", depinWSClient.getAccountContext().getClientAccount().getName());

        // 定时发送心跳
        JSONObject pingFrame = new JSONObject();
        pingFrame.put("id", SystemInfo.INSTANCE.getRandomId(26));
        pingFrame.put("type","heartbeat");

        JSONObject data = new JSONObject();
        data.put("version", "0.1.7");
        data.put("mostRecentModel", "unknown");
        data.put("status", "active");

        pingFrame.put("data", data);

        return pingFrame;
    }


    /**
     * 注册
     *
     * @return 注册
     */
    private String registerAccount() {
        OasisBotConfig oasisBotConfig = (OasisBotConfig) getBaseDepinBotConfig();
        String inviteCode = oasisBotConfig.getInviteCode();

        log.info("开始注册账户");
        AtomicInteger successCount = new AtomicInteger(0);
        List<CompletableFuture<Void>> futureList = getAccounts().stream().map(accountContext -> {
            try {
                concurrentSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String email = accountContext.getClientAccount().getEmail();

            NetworkProxy proxy = accountContext.getProxy();

            log.info("注册[{}]..使用邀请码[{}]..代理[{}-{}]", email, inviteCode, proxy.getId(), proxy.getAddress());
            return oasisApi.registerUser(proxy, email, accountContext.getClientAccount().getPassword(), inviteCode)
                    .exceptionally(throwable -> {
                        log.error("注册[{}]时发生异常", email, throwable);
                        return false;
                    })
                    .thenAcceptAsync(success -> {
                        if (success) {
                            accountContext.setUsable(true);
                            successCount.getAndIncrement();
                        } else {
                            accountContext.setUsable(false);
                        }
                    }).whenCompleteAsync((Void, throwable) -> {
                        concurrentSemaphore.release();
                    });
        }).toList();


        try {
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).get();
        } catch (InterruptedException | ExecutionException e) {
            return "等待账户注册完成时发生错误" + e.getMessage();
        }

        return "注册完成,成功注册" + successCount.get() + "个账户" + "共:" + getAccounts().size() + "个账户";
    }


    /**
     * 登录获取token
     *
     * @return token
     */
    private String loginAndTakeToken() {

        List<CompletableFuture<Void>> futures = getAccounts().stream().map(accountContext -> {
            try {
                concurrentSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String email = accountContext.getClientAccount().getEmail();
            NetworkProxy proxy = accountContext.getProxy();

            return oasisApi
                    .loginUser(proxy, email, accountContext.getClientAccount().getPassword())
                    .thenAccept(token -> {
                        if (StrUtil.isNotBlank(token)) {
                            log.info("邮箱[{}]登录成功，token[{}]", email, token);

                            accountContext.setParam("token", token);
                        }
                    }).exceptionally(throwable -> {
                        log.error("邮箱[{}]登录失败, {}", email, throwable.getMessage());
                        return null;
                    });
        }).toList();

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
        } catch (InterruptedException | ExecutionException e) {
            return "等待token获取完成发生错误，" + e.getMessage();
        }

        return "token获取完成，共:" + getAccounts().size() + "个账户";
    }

    /**
     * 重发验证邮件
     *
     * @return 打印的字符串
     */
    private String resendCode() {
        List<CompletableFuture<Void>> futures = getAccounts().stream().map(accountContext -> {
            try {
                concurrentSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String email = accountContext.getClientAccount().getEmail();
            NetworkProxy proxy = accountContext.getProxy();

            return oasisApi
                    .resendCode(proxy, email)
                    .thenAccept(success -> {
                        if (success) {
                            log.info("重发邮件[{}]成功", email);
                        }
                        concurrentSemaphore.release();
                    }).exceptionally(throwable -> {
                        log.error("邮箱[{}]重发验证邮件失败, {}", email, throwable.getMessage());
                        return null;
                    });
        }).toList();

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
        } catch (InterruptedException | ExecutionException e) {
            return "等待token获取完成发生错误，" + e.getMessage();
        }

        return "token获取完成，共:" +  getAccounts().size() + "个账户";
    }

    /**
     * 创建随机的系统数据
     *
     * @return JSONObject
     */
    private static @NotNull JSONObject generateRandomSystemData() {
        JSONObject systemData = new JSONObject();

        systemData.put("id", SystemInfo.INSTANCE.getRandomId(26));
        systemData.put("type", "system");

        JSONObject data = new JSONObject();
        data.put("gpuInfo", SystemInfo.INSTANCE.generateRandomGpuInfo());
        data.put("cpuInfo", SystemInfo.INSTANCE.generateRandomCpuInfo());
        data.put("memoryInfo", SystemInfo.INSTANCE.generateRandomMemoryInfo());
        data.put("machineId", SystemInfo.INSTANCE.getRandomId(32).toLowerCase());
        data.put("operatingSystem", SystemInfo.INSTANCE.generateRandomOperatingSystem());

        systemData.put("data", data);
        return systemData;
    }
}
package cn.com.helei.DepinBot.openLedger;

import cn.com.helei.DepinBot.core.BaseDepinBotConfig;
import cn.com.helei.DepinBot.core.pool.account.DepinClientAccount;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ToString
public class OpenLedgerConfig extends BaseDepinBotConfig {


    /**
     * 浏览器标识
     */
    private String origin;

    /**
     * 账户列表
     */
    private List<OpenLedgerAccount> openLedgerAccounts;


    public static void main(String[] args) {
        OpenLedgerConfig openLedgerConfig = loadYamlConfig("app/openledger.yaml");
        System.out.println(openLedgerConfig);
    }

    public static OpenLedgerConfig loadYamlConfig(String classpath) {
        Yaml yaml = new Yaml();
        log.info("开始加载OpenLedger配置信息-file classpath:[{}}", classpath);
        try (InputStream inputStream = OpenLedgerConfig.class.getClassLoader().getResourceAsStream(classpath)) {
            Map<String, Object> yamlData = yaml.load(inputStream);
            Map<String, Object> depin = (Map<String, Object>) yamlData.get("depin");
            Map<String, Object> openledger = (Map<String, Object>) depin.get("openledger");

            //Step 1 基础配置文件
            OpenLedgerConfig openLedgerConfig = yaml.loadAs(yaml.dump(openledger), OpenLedgerConfig.class);

            //Step 4 账户列表完善
            openLedgerConfig.getOpenLedgerAccounts().forEach(openLedgerAccount -> {
                openLedgerAccount.setOpenLedgerConfig(openLedgerConfig);
            });

            log.info("OpenLedger配置信息加载完毕: 共{}个账号", openLedgerConfig.getOpenLedgerAccounts().size());

            return openLedgerConfig;
        } catch (IOException e) {
            throw new RuntimeException(String.format("价值配置网络代理池文件[%s]发生错误", classpath));
        }
    }



    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class OpenLedgerAccount extends DepinClientAccount {

        private final static String printTemplate = "%-25s\t%-25s\t%-5s";

        private transient OpenLedgerConfig openLedgerConfig;

        private String token;

        private String identity;

        private String ownerAddress;


        public static String printTittle() {
            return String.format(printTemplate, "账户名", "代理", "环境ID");
        }

        @Override
        public String getConnectUrl() {
            return openLedgerConfig.getWsBaseUrl() + "?authToken=" + token;
        }

        @Override
        public HashMap<String, String> getWSHeaders() {
            HashMap<String, String> headers = new HashMap<>();

            headers.put("Upgrade", "websocket");
            headers.put("Origin", openLedgerConfig.origin);
            headers.put("Host", "apitn.openledger.xyz");
            headers.put("Connection", "Upgrade");

            return headers;
        }

        @Override
        public  HashMap<String, String>  getRestHeaders() {
            HashMap<String, String> headers = new HashMap<>();

            headers.put("authorization", "Bearer " + token);

            return headers;
        }

        @Override
        public String toString() {
            return "OpenLedgerAccount{" +
                    "token='" + token + '\'' +
                    ", identity='" + identity + '\'' +
                    ", ownerAddress='" + ownerAddress + '\'' +
                    "} " + super.toString();
        }
    }
}
