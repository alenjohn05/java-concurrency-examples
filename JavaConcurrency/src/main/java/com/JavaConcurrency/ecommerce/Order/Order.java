package JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.Order;

public class Order {
    private int orderId;
    private int productId;
    private int quantity;

    public Order(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
