package 多线程.保护性暂停;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(1000);
        for (Integer id : Future.getIds()) {
            new Postman(id, id + "号快递到了").start();
        }
    }
}
class People extends Thread{
    @Override
    public void run() {
        // 收信
        GuardedObject guardedObject = Future.createGuardedObject();
        System.out.println("开始收信i d:{}"+guardedObject.getId());
        Object mail = guardedObject.get(5000);
        System.out.println("收到信id:{}，内容:{}"+guardedObject.getId()+mail);
    }
}

class Postman extends Thread{
    private int id;
    private String mail;
    //构造方法
    public Postman(int id, String mail){
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Future.getGuardedObject(id);
        System.out.println("开始送信i d:{}，内容:{}"+guardedObject.getId()+mail);
        guardedObject.set(mail);
    }
}
class GuardedObject{
    private int id;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    private final Object lock = new Object();
    private Object response;
    public Object get(long timeLimit){
        synchronized (lock){
            if(timeLimit<0){
                throw new RuntimeException("等待时间不能小于0");
            } else if(timeLimit == 0){
                while (response == null){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                long begin = System.currentTimeMillis();
                long waitTime = 0;
                while (response == null){
                    if(waitTime>timeLimit){
                        break;
                    }
                    try {
                        lock.wait(timeLimit - waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitTime = System.currentTimeMillis() - begin;
                }
            }
            return response;
        }
    }

    public void set(Object res){
        synchronized (lock){
            this.response = res;
            lock.notifyAll();
        }
    }
}

class Future{
    private static final Map<Integer, GuardedObject> map = new Hashtable<>();
    private static int id = 0;
    private static int getGuardId(){
        return ++id;
    }
    public static GuardedObject createGuardedObject(){
        GuardedObject object = new GuardedObject();
        int id = getGuardId();
        object.setId(id);
        map.put(id, object);
        return object;
    }
    public static GuardedObject getGuardedObject(int id){
        return map.get(id);
    }
    public static Set<Integer> getIds() {
        return map.keySet();
    }
}
