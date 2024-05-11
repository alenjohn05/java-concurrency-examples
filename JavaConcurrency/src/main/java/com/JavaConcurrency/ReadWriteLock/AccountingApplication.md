Certainly! Here's an example of an accounting application using ReadWriteLock to manage concurrent access to account balances:

```java
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
```

In this example:

- We have a class `AccountingApplication` that manages account balances using a map `accountBalances`.
- We create a `ReentrantReadWriteLock` object named `lock`.
- We obtain read and write locks from the `lock` object using `readLock()` and `writeLock()` methods.
- The `getAccountBalance` method demonstrates read access to the account balances. It acquires the read lock, retrieves the balance, and then releases the lock.
- The `deposit` and `withdraw` methods demonstrate write access to the account balances. They acquire the write lock, update the balances, and then release the lock.
- In the `main` method, we create an instance of `AccountingApplication` and perform deposit and withdraw operations on account balances.
- Multiple threads can concurrently read the account balances using the read lock, but only one thread can hold the write lock for deposit and withdraw operations, ensuring exclusive access when modifying the account balances.