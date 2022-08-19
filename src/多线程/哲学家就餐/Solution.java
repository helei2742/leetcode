package 多线程.哲学家就餐;

import java.util.concurrent.locks.ReentrantLock;

public class Solution {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");//...
        Chopstick c2 = new Chopstick("2");//...
        Chopstick c3 = new Chopstick("3");//...
        Chopstick c4 = new Chopstick("4");//...
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }
}

class Philosopher extends Thread {
    public Philosopher(String name, Chopstick l, Chopstick r){
        this.name = name;
        this.left = l;
        this.right = r;
    }
    String name;
    Chopstick left;
    Chopstick right;
    public void run() {
        while (true) {
            // 尝试获得左手筷子
            if (left.tryLock()) {
                try {
                    // 尝试获得右手筷子
                    if (right.tryLock()) {
                        try {
                            System.out.println(name+"eating...l:"+left+"   r:"+right);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }
}
class Chopstick extends ReentrantLock {
    String name;
    public Chopstick(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}