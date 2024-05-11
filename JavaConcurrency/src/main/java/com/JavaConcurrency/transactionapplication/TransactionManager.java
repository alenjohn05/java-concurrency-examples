package JavaConcurrency.src.main.java.com.JavaConcurrency.transactionapplication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionManager {
    private final Lock lock = new ReentrantLock();

    public void performTransaction(Account account, int amount) {
        lock.lock();
        try {

            AccountTransaction transaction = new AccountTransaction(account, amount);
            Thread thread = new Thread(transaction);
            thread.start();
            thread.join(); // Wait for the transaction to complete
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
    }

}
