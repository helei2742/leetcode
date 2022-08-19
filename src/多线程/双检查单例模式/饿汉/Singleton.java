package 多线程.双检查单例模式.饿汉;

import java.io.Serializable;

public final class Singleton implements Serializable {
    private final static Singleton INSTANCE = new Singleton();
    private Singleton(){}
    public static Singleton getINSTANCE(){
        return INSTANCE;
    }

    //防止反序列化破坏单里
    public Object readResovle(){
        return INSTANCE;
    }
}
