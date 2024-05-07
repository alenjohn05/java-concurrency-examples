package JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Order;

import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.product.Product;
import JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.product.ProductManager;

public class OrderProcessor {
    public void processOrder(Order order) {
        Product product = ProductManager.getProduct(order.getProductId());
        if (product != null) {
            double totalPrice = product.getPrice() * order.getQuantity();
            System.out.println("Order " + order.getOrderId() + ": Total price: $" + totalPrice);
        } else {
            System.out.println("Order " + order.getOrderId() + ": Product not found.");
        }
    }
}
