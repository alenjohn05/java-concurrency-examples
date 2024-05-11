Sure, here's an example of an insurance application where multiple clients can request insurance quotes concurrently, and a thread-safe mechanism is employed to handle these requests using `ReentrantLock`.

```java
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
```

In this example:

- `InsuranceQuotes` class represents the insurance quotes generator. It has a `generateQuote` method that generates a quote for a given client name and stores it in a map. The `generateQuote` method is synchronized using `ReentrantLock` to ensure thread safety when multiple clients request quotes concurrently.
- `Client` class represents a client who requests an insurance quote. Each client is a separate thread. When a client thread runs, it requests a quote from the `InsuranceQuotes` object.
- In the `InsuranceApplication` class, two client threads are created to simulate concurrent quote requests.
- The `join` method is used to wait for both client threads to finish their requests before printing the final quotes.

This example demonstrates how `ReentrantLock` can be used to ensure thread safety in an insurance application where multiple clients are requesting quotes concurrently, preventing race conditions and ensuring data consistency in the quote generation process.