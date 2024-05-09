package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance;
    private final Lock lock;

    public BankAccount(int initialBalance) {
        balance = initialBalance;
        lock = new ReentrantLock();
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn $" + amount + ". New balance: $" + balance);
            } else {
                System.out.println("Insufficient funds to withdraw $" + amount);
            }
        } finally {
            lock.unlock();
        }
    }

    public void transfer(BankAccount recipient, int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                recipient.deposit(amount);
                System.out.println("Transferred $" + amount + " to another account. New balance: $" + balance);
            } else {
                System.out.println("Insufficient funds to transfer $" + amount);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Create multiple threads for deposit, withdrawal, and transfer operations
        Thread depositThread = new Thread(() -> {
            account.deposit(500);
        });

        Thread withdrawalThread = new Thread(() -> {
            account.withdraw(200);
        });

        BankAccount recipient = new BankAccount(0);
        Thread transferThread = new Thread(() -> {
            account.transfer(recipient, 300);
        });

        // Start the threads
        depositThread.start();
        withdrawalThread.start();
        transferThread.start();
    }
}
