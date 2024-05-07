package JavaConcurrency.src.main.java.com.JavaConcurrency.ecommerce.product;

import java.util.HashMap;
import java.util.Map;

public class ProductManager {

    private static Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "Laptop", 999.99));
        products.put(2, new Product(2, "Smartphone", 599.99));
        products.put(3, new Product(3, "Tablet", 299.99));
    }

    public static Product getProduct(int productId) {
        return products.get(productId);
    }

}
