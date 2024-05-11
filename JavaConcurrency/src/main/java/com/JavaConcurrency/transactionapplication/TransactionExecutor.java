package JavaConcurrency.src.main.java.com.JavaConcurrency.transactionapplication;

public class TransactionExecutor {
    public void executeTransactions(Account account, int numTransactions, int transactionAmount) {
        TransactionManager transactionManager = new TransactionManager();
        for (int i = 0; i < numTransactions; i++) {
            transactionManager.performTransaction(account, transactionAmount);
        }
    }
}
