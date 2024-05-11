package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

import java.util.ArrayList;
import java.util.List;

class Inventory {
    private List<String> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public synchronized void addItem(String item) {
        items.add(item);
        System.out.println("Added item: " + item);
    }

    public synchronized boolean purchaseItem(String item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println("Purchased item: " + item);
            return true;
        } else {
            System.out.println("Item " + item + " not available");
            return false;
        }
    }
}

class Customer extends Thread {
    private Inventory inventory;

    public Customer(Inventory inventory) {
        this.inventory = inventory;
    }

    public void run() {
        // Simulate browsing and purchasing
        String itemToPurchase = "Item1";
        boolean purchased = inventory.purchaseItem(itemToPurchase);
        if (purchased) {
            System.out.println("Customer " + this.getName() + " purchased " + itemToPurchase);
        }
    }
}

public class ECommerceExample {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem("Item1");
        inventory.addItem("Item2");

        // Create multiple customers
        for (int i = 1; i <= 5; i++) {
            Customer customer = new Customer(inventory);
            customer.setName("Customer-" + i);
            customer.start();
        }
    }
}
