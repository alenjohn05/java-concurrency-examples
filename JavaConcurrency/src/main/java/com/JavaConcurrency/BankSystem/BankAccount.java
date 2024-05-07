package JavaConcurrency.src.main.java.com.JavaConcurrency.BankSystem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance;
    private final Lock lock;

    public BankAccount(int intialbalance) {
        this.balance = intialbalance;
        lock = new ReentrantLock();
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            balance -= amount;
            System.out.println("Withdrawed $" + amount + ". New balance: $" + balance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }

    }

    public void transfer(BankAccount receipent, int money) {
        lock.lock();
        try {
            if (balance >= money) {
                balance -= money;
                receipent.deposit(money);
                System.out.println("Transferred $" + money + " to another account. New balance: $" + balance);
            } else {
                System.out.println("Insufficient funds to transfer $" + money);
            }
        } finally {
            lock.unlock();
        }
    }
}
