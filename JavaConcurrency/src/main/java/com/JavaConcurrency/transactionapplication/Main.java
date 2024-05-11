package JavaConcurrency.src.main.java.com.JavaConcurrency.transactionapplication;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(1000);
        TransactionExecutor executor = new TransactionExecutor();
        executor.executeTransactions(account, 5, 200);
        System.out.println("Final balance: " + account.getBalance());
    }
}