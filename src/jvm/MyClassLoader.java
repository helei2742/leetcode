package jvm;

import java.io.*;

public class MyClassLoader extends ClassLoader{
    private String byteCodePath;
    MyClassLoader(String byteCodePath){
        this.byteCodePath = byteCodePath;
    }
    MyClassLoader(String byteCodePath, ClassLoader parent){
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String classname) throws ClassNotFoundException {
        String fileName = this.byteCodePath + classname + ".class";
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(fileName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while((len = bis.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            byte[] byteCodes = baos.toByteArray();

            return defineClass(null, byteCodes, 0, buffer.length);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
