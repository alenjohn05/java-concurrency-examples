package JavaConcurrency.src.main.java.com.JavaConcurrency.messageApplication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class MessageQueue {
    private final BlockingQueue<Message> queue;

    public MessageQueue() {
        queue = new ArrayBlockingQueue<>(100);
    }

    public void sendMessage(Message message) throws InterruptedException {
        queue.put(message);
        System.out.println("Message sent from " + message.getSender() + " to " + message.getReceiver() + ": "
                + message.getContent());
    }

    public Message receiveMessage(String user) throws InterruptedException {
        Message message = queue.take();
        System.out
                .println("Message received by " + user + " from " + message.getSender() + ": " + message.getContent());
        return message;
    }
}