package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

class Counter {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }
}

public class CounterApp {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Creating and starting multiple threads to increment the counter
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {

            }
        }

        // Wait for all threads to finish
        try {
            Thread.sleep(1000); // Give some time for threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final count
        System.out.println("Final count: " + counter.getCount());
    }
}
