package 多线程.享元模式_链接池;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Pool {
    private int poolSize;
    private Connection[] connections;
    private AtomicIntegerArray states;

    public Pool(int size){
        this.poolSize = size;
        connections = new Connection[size];
        for (int i = 0; i < size; i++) {
//            connections[i] = new
        }
        states = new AtomicIntegerArray(new int[size]);
    }

    public Connection borrow(){
        while (true) {
            for (int i = 0; i < this.poolSize; i++) {
                if(states.get(i) == 0){
                    if(states.compareAndSet(i, 0, 1)){
                        return connections[i];
                    }
                }
            }

            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void free(Connection connection) {
        for (int i = 0; i < poolSize; i++) {
            if(connections[i] == connection){
                states.set(i, 0);
            }
            synchronized (this){
                this.notifyAll();
            }
        }
    }
}
