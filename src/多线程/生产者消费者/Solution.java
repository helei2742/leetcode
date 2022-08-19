package 多线程.生产者消费者;

import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(()->{
                System.out.println("生产者者"+id+"生产");
                queue.put(new Message(id, "消息"+id));

            },"生产者"+id).start();
        }

        new Thread(()->{
            while (true){
                Message take = queue.take();
                System.out.println("消费者拿到"+take.message);
            }
        },"消费者").start();

    }
}

class Message{
    public int id;
    public Object message;
    public Message(int id, Object message){
        this.id = id;
        this.message = message;
    }

}
class MessageQueue{
    private final LinkedList<Message> queue = new LinkedList<>();
    private int max;
    public MessageQueue(int max){
        this.max = max;
    }

    public Message take(){
        synchronized (queue){
            while (queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = queue.poll();
            queue.notifyAll();
            return message;
        }
    }

    public void put(Message message){
        synchronized (queue){
            while (queue.size() == max){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(message);
            queue.notifyAll();
        }
    }
}