package JavaConcurrency.src.main.java.com.JavaConcurrency.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountingApplication {
    private Map<String, Double> accountBalances = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public double getAccountBalance(String accountNumber) {
        readLock.lock();

        try {
            return accountBalances.getOrDefault(accountNumber, 0.0);
        } finally {
            readLock.unlock();
        }
    }

    public void deposit(String accountNumber, double amount) {
        writeLock.lock();
        try {
            double balance = accountBalances.getOrDefault(accountNumber, 0.0);
            balance += amount;
            accountBalances.put(accountNumber, balance);
            System.out.println("Deposited " + amount + " into account " + accountNumber);
        } finally {
            writeLock.unlock();
        }
    }

    public void withdraw(String accountNumber, double amount) {
        writeLock.lock();
        try {
            double balance = accountBalances.getOrDefault(accountNumber, 0.0);
            if (balance >= amount) {
                balance -= amount;
                accountBalances.put(accountNumber, balance);
                System.out.println("Withdrawn " + amount + " from account " + accountNumber);
            } else {
                System.out.println("Insufficient funds in account " + accountNumber);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        AccountingApplication accountingApp = new AccountingApplication();

        // Deposit and withdraw operations
        accountingApp.deposit("1001", 1000);
        accountingApp.withdraw("1001", 500);

        // Print account balances
        System.out.println("Account balance for account 1001: " + accountingApp.getAccountBalance("1001"));
    }
}
