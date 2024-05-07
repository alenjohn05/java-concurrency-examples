package JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Concurrency;

import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Order.Order;
import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Order.OrderProcessor;

public class OrderWorker implements Runnable {
    private Order order;

    public OrderWorker(Order order) {
        this.order = order;
    }

    public void run() {
        OrderProcessor orderProcessor = new OrderProcessor();

        orderProcessor.processOrder(order);

    }

}
