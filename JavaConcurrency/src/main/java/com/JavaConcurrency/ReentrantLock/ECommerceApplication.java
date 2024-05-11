package JavaConcurrency.src.main.java.com.JavaConcurrency.ReentrantLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ECommerceApplication {
    public static void main(String[] args) {
        ECommerceStore store = new ECommerceStore();
        Customer customer1 = new Customer(store, "Alice");
        Customer customer2 = new Customer(store, "Bob");

        customer1.start();
        customer2.start();

        try {
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final inventory:");
        store.printInventory();
    }
}

class ECommerceStore {
    private Map<String, Integer> inventory;
    private Lock lock;

    public ECommerceStore() {
        inventory = new HashMap<>();
        inventory.put("Laptop", 10);
        inventory.put("Phone", 20);
        lock = new ReentrantLock();
    }

    public void purchase(String item, int quantity) {
        lock.lock();
        try {
            if (inventory.containsKey(item)) {
                int availableQuantity = inventory.get(item);
                if (availableQuantity >= quantity) {
                    inventory.put(item, availableQuantity - quantity);
                    System.out.println(Thread.currentThread().getName() + " purchased " + quantity + " " + item);
                } else {
                    System.out.println("Insufficient stock for " + item);
                }
            } else {
                System.out.println("Item " + item + " not found in inventory");
            }
        } finally {
            lock.unlock();
        }
    }

    public void printInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class Customer extends Thread {
    private ECommerceStore store;
    private String itemName;

    public Customer(ECommerceStore store, String itemName) {
        this.store = store;
        this.itemName = itemName;
    }

    @Override
    public void run() {
        store.purchase(itemName, 5); // Each customer purchases 5 items
    }
}
