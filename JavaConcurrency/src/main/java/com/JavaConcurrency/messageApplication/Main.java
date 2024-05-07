package JavaConcurrency.src.main.java.com.JavaConcurrency.messageApplication;

public class Main {
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
