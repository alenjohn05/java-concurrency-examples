Create an example of an eCommerce application that utilizes Java packages for concurrency and threading. In this example, we'll simulate the processing of orders concurrently using multiple threads.

First, let's define the structure of our eCommerce application:

1. **Product Package**: Contains classes related to products in the store.
2. **Order Package**: Contains classes related to order processing.
3. **Concurrency Package**: Contains classes for managing concurrency and threading.

Here's how the directory structure might look:

```
ecommerce/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── ecommerce/
│   │   │   │   │   ├── product/
│   │   │   │   │   │   ├── Product.java
│   │   │   │   │   │   └── ProductManager.java
│   │   │   │   │   ├── order/
│   │   │   │   │   │   ├── Order.java
│   │   │   │   │   │   └── OrderProcessor.java
│   │   │   │   │   └── concurrency/
│   │   │   │   │       ├── OrderWorker.java
│   │   │   │   │       └── OrderExecutor.java
│   │   │   │   └── Main.java
```

Now, let's create the code for each package:

1. **Product Package**:

```java
// Product.java
package com.ecommerce.product;

public class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
}

// ProductManager.java
package com.ecommerce.product;

import java.util.HashMap;
import java.util.Map;

public class ProductManager {
    private static Map<Integer, Product> products = new HashMap<>();

    static {
        // Initialize some products
        products.put(1, new Product(1, "Laptop", 999.99));
        products.put(2, new Product(2, "Smartphone", 599.99));
        products.put(3, new Product(3, "Tablet", 299.99));
    }

    public static Product getProduct(int productId) {
        return products.get(productId);
    }
}
```

2. **Order Package**:

```java
// Order.java
package com.ecommerce.order;

import com.ecommerce.product.Product;

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

// OrderProcessor.java
package com.ecommerce.order;

import com.ecommerce.product.Product;
import com.ecommerce.product.ProductManager;

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
```

3. **Concurrency Package**:

```java
// OrderWorker.java
package com.ecommerce.concurrency;

import com.ecommerce.order.Order;
import com.ecommerce.order.OrderProcessor;

public class OrderWorker implements Runnable {
    private Order order;

    public OrderWorker(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.processOrder(order);
    }
}

// OrderExecutor.java
package com.ecommerce.concurrency;

import com.ecommerce.order.Order;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderExecutor {
    private static final int THREAD_POOL_SIZE = 5;

    public void executeOrders(List<Order> orders) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (Order order : orders) {
            executor.submit(new OrderWorker(order));
        }
        executor.shutdown();
    }
}
```

4. **Main Class**:

```java
// Main.java
package com.ecommerce;

import com.ecommerce.concurrency.OrderExecutor;
import com.ecommerce.order.Order;

import java.util.ArrayList;
import java.util.List;

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
```

In this example:

- The `Product` and `ProductManager` classes handle products and product management.
- The `Order` and `OrderProcessor` classes handle orders and order processing.
- The `OrderWorker` class implements the `Runnable` interface to process orders concurrently.
- The `OrderExecutor` class manages the execution of orders using a thread pool.

This example demonstrates how packages, concurrency, and threading can be used together in a real-world scenario such as an eCommerce application.