package JavaConcurrency.src.main.java.com.JavaConcurrency.ReentrantLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InsuranceApplication {
    public static void main(String[] args) {
        InsuranceQuotes quotes = new InsuranceQuotes();
        Client client1 = new Client(quotes, "Alice");
        Client client2 = new Client(quotes, "Bob");

        client1.start();
        client2.start();

        try {
            client1.join();
            client2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        quotes.printQuotes();
    }
}

class InsuranceQuotes {
    private Map<String, Double> quotes;
    private Lock lock;

    public InsuranceQuotes() {
        quotes = new HashMap<>();
        lock = new ReentrantLock();
    }

    public void generateQuote(String clientName) {
        lock.lock();
        try {
            // Simulate generating quote
            double quoteAmount = Math.random() * 1000;
            quotes.put(clientName, quoteAmount);
            System.out.println("Quote generated for " + clientName + ": $" + quoteAmount);
        } finally {
            lock.unlock();
        }
    }

    public void printQuotes() {
        for (Map.Entry<String, Double> entry : quotes.entrySet()) {
            System.out.println("Client: " + entry.getKey() + ", Quote: $" + entry.getValue());
        }
    }
}

class Client extends Thread {
    private InsuranceQuotes quotes;
    private String clientName;

    public Client(InsuranceQuotes quotes, String clientName) {
        this.quotes = quotes;
        this.clientName = clientName;
    }

    @Override
    public void run() {
        quotes.generateQuote(clientName);
    }
}
