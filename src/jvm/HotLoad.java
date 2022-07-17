package jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HotLoad {
    public static void main(String[] args) {
        String rootDir = "/Users/helei/develop/ideaworkspace/leetcode/src";


        try {
            while (true){
                UserClassloader loader = new UserClassloader(rootDir);

                Class aClass = loader.findClass("test.User");

                Object o = aClass.newInstance();
                Method method = aClass.getMethod("method");

                method.invoke(o);
                Thread.sleep(5000);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
