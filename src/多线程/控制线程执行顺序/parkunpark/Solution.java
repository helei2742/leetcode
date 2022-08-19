package 多线程.控制线程执行顺序.parkunpark;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class Solution {
    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {

        ParkUnpark parkUnpark = new ParkUnpark(5);

        t1 = new Thread(() -> {
            parkUnpark.print("a", t2);
        });
        t2 = new Thread(() -> {
            parkUnpark.print("b", t3);
        });
        t3 = new Thread(() -> {
            parkUnpark.print("c", t1);
        });
        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}

class ParkUnpark{
    private int loop;
    public ParkUnpark(int l){
        this.loop = l;
    }

    public void print(String str, Thread thread){
        for (int i = 0; i < loop; i++) {

            LockSupport.park();
            System.out.println(str);
            LockSupport.unpark(thread);
        }
    }
}
