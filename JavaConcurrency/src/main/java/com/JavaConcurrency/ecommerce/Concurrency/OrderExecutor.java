package JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Concurrency;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Order.Order;

public class OrderExecutor {
    private static final int THREAD_POOL_SIZE = 5;

    public void executeOrders(List<Order> orders) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (Order order : orders) {
            executorService.submit(new OrderWorker(order));

        }
        executorService.shutdown();

    }

}
