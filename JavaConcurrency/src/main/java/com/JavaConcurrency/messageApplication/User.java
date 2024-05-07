package JavaConcurrency.src.main.java.com.JavaConcurrency.messageApplication;

class User implements Runnable {
    private final String name;
    private final MessageQueue messageQueue;

    public User(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    public void sendMessage(String receiver, String content) throws InterruptedException {
        messageQueue.sendMessage(new Message(name, receiver, content));
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                sendMessage("user2", "Hello from " + name + " - Message " + (i + 1));
                Thread.sleep(1000);
                messageQueue.receiveMessage(name);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
