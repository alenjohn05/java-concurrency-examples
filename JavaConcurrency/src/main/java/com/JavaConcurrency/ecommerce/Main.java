package JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce;

import java.util.ArrayList;
import java.util.List;

import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Concurrency.OrderExecutor;
import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Order.Order;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = generateOrders();
        OrderExecutor orderExecutor = new OrderExecutor();
        orderExecutor.executeOrders(orders);
    }

    private static List<Order> generateOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, 1, 2)); // Order ID, Product ID, Quantity
        orders.add(new Order(2, 2, 1));
        orders.add(new Order(3, 3, 3));
        orders.add(new Order(4, 1, 1));
        orders.add(new Order(5, 2, 2));
        return orders;
    }

}
