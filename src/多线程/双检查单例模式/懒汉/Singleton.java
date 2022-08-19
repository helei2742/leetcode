package 多线程.双检查单例模式.懒汉;

import java.io.Serializable;

public final class Singleton implements Serializable {
    private volatile static Singleton INSTANCE = null;
    private Singleton(){}
    public static Singleton getINSTANCE(){
        if(INSTANCE == null){
            synchronized(Singleton.class){
                if(INSTANCE == null){
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    //防止反序列化破坏单里
    public Object readResovle(){
        return INSTANCE;
    }
}
