package JavaConcurrency.src.main.java.com.JavaConcurrency.transactionapplication;

public class AccountTransaction implements Runnable {
    private final Account account;
    private final int transactionAmount;

    public AccountTransaction(Account account, int transactionAmount) {
        this.account = account;
        this.transactionAmount = transactionAmount;
    }

    @Override
    public void run() {
        account.withdraw(transactionAmount);
    }

}
