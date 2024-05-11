Certainly! Below is a simple example of a banking application using `ReentrantLock` to ensure thread safety when multiple accounts are being accessed concurrently.

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankingApplication {
    public static void main(String[] args) {
        Account account1 = new Account(1000);
        Account account2 = new Account(2000);

        // Create and start threads performing transactions on accounts
        Thread thread1 = new Thread(() -> {
            account1.transfer(account2, 500);
        });

        Thread thread2 = new Thread(() -> {
            account2.transfer(account1, 300);
        });

        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final balances
        System.out.println("Final balance of account1: " + account1.getBalance());
        System.out.println("Final balance of account2: " + account2.getBalance());
    }
}

class Account {
    private double balance;
    private Lock lock = new ReentrantLock();

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public void transfer(Account destination, double amount) {
        lock.lock();
        try {
            withdraw(amount);
            destination.deposit(amount);
            System.out.println(Thread.currentThread().getName() + " transferred " + amount + " from "
                    + this.getClass().getSimpleName() + " to " + destination.getClass().getSimpleName());
        } finally {
            lock.unlock();
        }
    }
}
```

In this example:

- `Account` class represents a bank account with methods for depositing, withdrawing, getting the balance, and transferring funds between accounts. Each account object has its own `ReentrantLock` for ensuring thread safety.
- The `transfer` method performs a transfer of funds from one account to another while ensuring that the operation is thread-safe using the `ReentrantLock`.
- In the `BankingApplication` class, two threads are created to transfer funds between two accounts concurrently.
- The `join` method is used to wait for both threads to finish their execution before printing the final balances of the accounts.

This example demonstrates how `ReentrantLock` can be used to prevent race conditions and ensure thread safety in a banking application where multiple threads are performing transactions on shared resources.