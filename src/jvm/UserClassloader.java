package jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserClassloader extends ClassLoader {
    private String rootDir;


    UserClassloader(String rootDir){

        this.rootDir = rootDir;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if(classData == null){
            throw new ClassNotFoundException();
        }else{
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String classname) {
        String classPath = classNameToPath(classname);
        try{
            FileInputStream fis = new FileInputStream(classPath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;

            while((len = fis.read(buffer))!=-1){
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String classNameToPath(String classname) {
        return rootDir + "/" + classname.replace('.', '/') + ".class";
    }

}
