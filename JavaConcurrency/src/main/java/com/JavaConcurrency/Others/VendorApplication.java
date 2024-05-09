package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VendorApplication {
    private static Integer threadNumber = 9;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        executorService.submit(new VendorTask("Vendor A"));
        executorService.submit(new VendorTask("Vendor C"));
        executorService.submit(new VendorTask("Vendor B"));
    }
}

class VendorTask implements Runnable {
    private String vendorName;

    public VendorTask(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        System.out.println(
                "Vendor " + vendorName + " is processing orders on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Vendor " + vendorName + " has completed processing orders.");
    }
}
