package 多线程.控制线程执行顺序.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);

        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(()->{
            awaitSignal.print("a", a, b);
        }).start();
        new Thread(()->{
            awaitSignal.print("b", b, c);
        }).start();
        new Thread(()->{
            awaitSignal.print("c", c, a);
        }).start();

        Thread.sleep(1000);
        awaitSignal.lock();
        try{
            a.signal();
        }finally {
            awaitSignal.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock {
    private int loopNumber;
    public AwaitSignal(int l){
        this.loopNumber = l;
    }
    public void print(String msg, Condition condition, Condition next){
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try{
                condition.await();
                System.out.println(msg);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}
