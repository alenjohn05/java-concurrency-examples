package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterExample {
    private int count;

    // Method to increment the counter using synchronized keyword
    public synchronized void increment() {
        count++;
        System.out.println("Counter incremented by thread: " + Thread.currentThread().getName() + ", Count: " + count);
    }

    // Method to decrement the counter using synchronized block
    public void decrement() {
        synchronized (this) {
            count--;
            System.out.println(
                    "Counter decremented by thread: " + Thread.currentThread().getName() + ", Count: " + count);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CounterExample counterExample = new CounterExample();

        // Run multiple threads to increment the counter
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> counterExample.increment());
        }

        // Run multiple threads to decrement the counter
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> counterExample.decrement());
        }

        executor.shutdown();
    }
}
