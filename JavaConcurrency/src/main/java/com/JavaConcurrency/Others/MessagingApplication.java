package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Message {
    private final String sender;
    private final String receiver;
    private final String content;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }
}

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

class User implements Runnable {
    private final String name;
    private final MessageQueue messageQueue;

    public User(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    public void sendMessageContent(String receiver, String content) throws InterruptedException {
        messageQueue.sendMessage(new Message(name, receiver, content));
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                sendMessageContent("user2", "Hello from " + name + " - Message " + (i + 1));
                Thread.sleep(1000);
                messageQueue.receiveMessage(name);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MessagingApplication {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        User user1 = new User("user1", messageQueue);
        User user2 = new User("user2", messageQueue);

        Thread thread1 = new Thread(user1);
        Thread thread2 = new Thread(user2);

        thread1.start();
        thread2.start();
    }
}
