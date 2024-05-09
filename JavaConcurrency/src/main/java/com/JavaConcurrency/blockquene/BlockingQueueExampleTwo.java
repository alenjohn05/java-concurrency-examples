import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExampleTwo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        try {
            queue.put(1);
            queue.put(1);
            System.out.println("Element 1 added to the queue");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (queue.offer(2)) {
            System.out.println("Element 2 added to the queue");
        } else {
            System.out.println("Queue is full, cannot add element 2");
        }

        try {
            Integer takenElement = queue.take();
            System.out.println("Element " + takenElement + " taken from the queue");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

        Integer polledElement = queue.poll();
        if (polledElement != null) {
            System.out.println("Element " + polledElement + " polled from the queue");
        } else {
            System.out.println("Queue is empty, nothing to poll");
        }

        try {
            if (queue.offer(3, 2, TimeUnit.SECONDS)) {
                System.out.println("Element 3 added to the queue");
            } else {
                System.out.println("Timed out waiting to add element 3 to the queue");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            Integer polledElementWithTimeout = queue.poll(2, TimeUnit.SECONDS);
            if (polledElementWithTimeout != null) {
                System.out.println("Element " + polledElementWithTimeout + " polled from the queue with timeout");
            } else {
                System.out.println("Timed out waiting to poll element from the queue");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
