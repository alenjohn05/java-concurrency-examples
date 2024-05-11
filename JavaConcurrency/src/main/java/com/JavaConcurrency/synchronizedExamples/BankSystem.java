package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

class Bank {
    private int[] accounts;

    public Bank(int numAccounts, int initialBalance) {
        accounts = new int[numAccounts];
        for (int i = 0; i < numAccounts; i++) {
            accounts[i] = initialBalance;
        }
    }

    public synchronized void transfer(int from, int to, int amount) {
        if (accounts[from] < amount)
            return;
        accounts[from] -= amount;
        accounts[to] += amount;
        System.out.println(Thread.currentThread().getName() + " transferred $" + amount + " from account " + from
                + " to account " + to);
    }

    public synchronized int getTotalBalance() {
        int total = 0;
        for (int balance : accounts) {
            total += balance;
        }
        return total;
    }

    public int getNumAccounts() {
        return accounts.length;
    }
}

class TransferThread extends Thread {
    private Bank bank;
    private int fromAccount;
    private int maxAmount;
    private static final int REPEATS = 100;

    public TransferThread(Bank bank, int fromAccount, int maxAmount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < REPEATS; i++) {
                int toAccount = (int) (bank.getNumAccounts() * Math.random());
                int amount = (int) (maxAmount * Math.random());
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BankSystem {
    public static void main(String[] args) {
        final int NACCOUNTS = 10;
        final int INITIAL_BALANCE = 1000;
        final int MAX_AMOUNT = 1000;

        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            TransferThread t = new TransferThread(bank, i, MAX_AMOUNT);
            t.start();
        }
    }
}
