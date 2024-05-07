package JavaConcurrency.src.main.java.com.JavaConcurrency.bankSystem;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        BankAccount recipient = new BankAccount(0);

        Thread depositThread = new Thread(() -> {
            account.deposit(200);
        });
        Thread withDrawThread = new Thread(() -> {
            account.withdraw(200);
        });

        Thread transferThread = new Thread(() -> {
            account.transfer(recipient, 6000);
        });

        depositThread.start();
        withDrawThread.start();
        transferThread.start();

    }
}
