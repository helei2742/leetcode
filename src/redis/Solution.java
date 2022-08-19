package redis;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static Socket socket;
    static PrintWriter writer;
    static BufferedReader reader;

    public static void main(String[] args) {

        try{
            socket = new Socket();
            //1、建立链接
            String host = "10.211.55.4";
            int port = 6379;
            socket.connect(new InetSocketAddress(host, port));
            //2、获取输入输出流
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            //

            //发送请求
            sendRequest("set", "name1","huge");
            //解析响应
            Object obj = handleResponse();
            System.out.println(obj);

            //发送请求
            sendRequest("get", "name1");
            //解析响应
            Object obj1 = handleResponse();
            System.out.println(obj1);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //解析响应
    private static Object handleResponse() throws IOException {
        //读取首字节
        int prefix = reader.read();
        //判断数据类型表示
        switch (prefix){
            case '+': //单行字符
                return reader.readLine();
            case '-': //错误
                return new RuntimeException(reader.readLine());
            case ':': //数字
                return Long.parseLong(reader.readLine());
            case '$': //多行字符
                int len = Integer.parseInt(reader.readLine());
                if(len == -1) return null;
                if(len == 0) return "";

                //读len个字节，偷个懒，假设没有特殊字符
                return reader.readLine();
            case '*': //数组
                return readBuckString();
            default:
                return new RuntimeException("错误格式");
        }
    }
    //解析数组响应
    private static Object readBuckString() throws IOException {
        int len = Integer.parseInt(reader.readLine());
        if(len <= 0){
            return null;
        }
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(handleResponse());
        }
        return list;
    }
    //发送请求
    private static void sendRequest(String ... args) {
        writer.print("*"+args.length+"\r\n");
        for (String arg : args) {
            writer.print("$"+arg.getBytes(StandardCharsets.UTF_8).length+"\r\n");
            writer.print(arg+"\r\n");
        }
        writer.flush();
    }
}
