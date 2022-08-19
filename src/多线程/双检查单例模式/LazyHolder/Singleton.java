package 多线程.双检查单例模式.LazyHolder;

import java.io.Serializable;

public final class Singleton{
    private Singleton(){}
    private static class LazyHolder{
        static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return LazyHolder.INSTANCE;
    }
}
