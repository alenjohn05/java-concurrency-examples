package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(60);
        for (int i = 0; i < 50; i++) {
            executor.submit(new Task(i));
        }

        executor.shutdown();
    }

    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is being executed by thread " + Thread.currentThread().getName());
        }
    }
}
