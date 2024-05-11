package JavaConcurrency.src.main.java.com.JavaConcurrency.RaceCondition;

public class CounterExample {
    private int counter = 0;

    public void increment() {
        counter++; // Increment the counter
    }

    public void decrement() {
        counter--; // Decrement the counter
    }

    public int getCounter() {
        return counter; // Get the current value of the counter
    }

    public static void main(String[] args) throws InterruptedException {
        CounterExample example = new CounterExample();

        // Create and start two threads incrementing the counter
        Thread incrementThread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.increment();
            }
        });
        Thread incrementThread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.increment();
            }
        });

        incrementThread1.start();
        incrementThread2.start();

        // Wait for both threads to finish
        incrementThread1.join();
        incrementThread2.join();

        // Print the final counter value
        System.out.println("Final counter value: " + example.getCounter());
    }
}
