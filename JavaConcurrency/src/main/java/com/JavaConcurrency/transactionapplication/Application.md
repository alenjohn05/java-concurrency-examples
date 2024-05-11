Sure, let's create another example that focuses on learning about threads, runnables, and locks in Java. In this example, we'll create a simple banking application where multiple threads represent different account transactions, and we'll use locks to ensure thread safety.

Here's the directory structure for our new example:

```
bankingapp/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── bankingapp/
│   │   │   │   │   ├── account/
│   │   │   │   │   │   ├── Account.java
│   │   │   │   │   │   └── AccountTransaction.java
│   │   │   │   │   └── transaction/
│   │   │   │   │       ├── TransactionManager.java
│   │   │   │   │       └── TransactionExecutor.java
│   │   │   │   └── Main.java
```

1. **Account Package**:

```java
// Account.java
package com.bankingapp.account;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
}

// AccountTransaction.java
package com.bankingapp.account;

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
```

2. **Transaction Package**:

```java
// TransactionManager.java
package com.bankingapp.transaction;

import com.bankingapp.account.Account;
import com.bankingapp.account.AccountTransaction;

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

// TransactionExecutor.java
package com.bankingapp.transaction;

import com.bankingapp.account.Account;

public class TransactionExecutor {
    public void executeTransactions(Account account, int numTransactions, int transactionAmount) {
        TransactionManager transactionManager = new TransactionManager();
        for (int i = 0; i < numTransactions; i++) {
            transactionManager.performTransaction(account, transactionAmount);
        }
    }
}
```

3. **Main Class**:

```java
// Main.java
package com.bankingapp;

import com.bankingapp.account.Account;
import com.bankingapp.transaction.TransactionExecutor;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(1000);
        TransactionExecutor executor = new TransactionExecutor();
        executor.executeTransactions(account, 5, 200); // Simulate 5 withdrawals of 200 each
        System.out.println("Final balance: " + account.getBalance());
    }
}
```

In this example:

- The `Account` class represents a bank account with a balance.
- The `AccountTransaction` class implements `Runnable` and represents a transaction where money is withdrawn from the account.
- The `TransactionManager` class ensures thread safety using a `ReentrantLock` to synchronize access to account transactions.
- The `TransactionExecutor` class manages the execution of multiple transactions.
- In the `Main` class, we create an account, execute multiple transactions concurrently, and print the final balance.

This example demonstrates how to use locks for thread synchronization to ensure that account transactions are performed safely in a multi-threaded environment.