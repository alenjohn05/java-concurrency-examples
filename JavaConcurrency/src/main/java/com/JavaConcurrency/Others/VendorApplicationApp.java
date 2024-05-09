package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VendorApplicationApp {

    private static Integer threadnumber = 2;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(threadnumber);
        Vendor vendor1 = new Vendor("Vendor 1", executor);
        Vendor vendor2 = new Vendor("Vendor 2", executor);

        vendor1.processOrder("Order 1");
        vendor2.processOrder("Order 2");

        // Shutdown the ExecutorService when no more orders need to be processed
        executor.shutdown();
    }

}

class Vendor {
    private String taskname;
    private ExecutorService executor;

    public Vendor(String taskname, ExecutorService executor) {
        this.taskname = taskname;
        this.executor = executor;
    }

    public void processOrder(String order) {
        executor.submit(() -> {
            System.out.println(taskname + " is processing " + order + " on thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskname + " has completed processing " + order);
        });
    }

}