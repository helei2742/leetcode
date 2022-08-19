package 多线程.控制线程执行顺序;

public class Solution {

    public static void main(String[] args) {
        WaitNotify lock = new WaitNotify(0,0);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {

                int finalJ = j;
                int finalI = i;
                new Thread(()->{
                    synchronized (lock){
                        while (lock.flag!= finalJ ||lock.loop!= finalI){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(finalJ);
                        lock.flag = finalJ + 1;
                        if(lock.flag == 3){
                            lock.flag = 0;
                            lock.loop ++;
                        }
                        lock.notifyAll();
                    }
                }).start();
            }
        }
    }
}

class WaitNotify{
    public int flag;
    public int loop;
    public WaitNotify(int f, int l){
        this.flag = f;
        this.loop = l;
    }
}
