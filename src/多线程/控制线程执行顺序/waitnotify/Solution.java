package 多线程.控制线程执行顺序.waitnotify;

public class Solution {
    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(5, 1);
        new Thread(()->{
            waitNotify.print("a", 1, 2);
        }).start();
        new Thread(()->{
            waitNotify.print("b", 2, 3);
        }).start();
        new Thread(()->{
            waitNotify.print("c", 3, 1);
        }).start();
    }
}

class WaitNotify{

    private int loop;
    private int flag;
    public WaitNotify(int l, int f){
        this.loop = l;
        this.flag = f;
    }

    public void print(String msg, int cur, int next){
        for (int i = 0; i < loop; i++) {
            synchronized (this){
                while (cur!=flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(msg);
                flag = next;
                this.notifyAll();
            }
        }
    }
}
