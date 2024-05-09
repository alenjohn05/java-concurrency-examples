import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                this.messageQueue.setQueueValue(i);
                System.out.println("Produced: " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private final MessageQueue messageQueue;

    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Integer value = this.messageQueue.getQueueValue();
                System.out.println("Consumed: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MessageQueue {
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public Integer getQueueValue() throws InterruptedException {

        Integer data = this.queue.take();
        return data;
    }

    public void setQueueValue(Integer value) throws InterruptedException {
        this.queue.put(value);
    }

}

public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        Producer producerRunnable = new Producer(messageQueue);
        Consumer consumerRunnable = new Consumer(messageQueue);

        Thread producer = new Thread(producerRunnable);

        Thread consumer = new Thread(consumerRunnable);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }

}
