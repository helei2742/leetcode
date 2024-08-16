package 多线程.返回值;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                System.out.println("执行完任务返回结果：123");
                Thread.sleep(2000);
                return "123";
            }
        });

        String s = future.get();
        System.out.println(s);
    }
}
