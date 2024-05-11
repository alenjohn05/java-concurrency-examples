package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

import java.util.HashMap;
import java.util.Map;

public class InsuranceApp {
    public static void main(String[] args) {
        PolicyManager policyManager = new PolicyManager();

        // Create and start multiple threads to perform policy operations
        Thread thread1 = new Thread(new PolicyOperator(policyManager, "Policy1", 1000));
        Thread thread2 = new Thread(new PolicyOperator(policyManager, "Policy2", 1500));

        thread1.start();
        thread2.start();
    }
}

class PolicyManager {
    private Map<String, Double> policyData = new HashMap<>();

    public PolicyManager() {
        // Initialize some sample policy data
        policyData.put("Policy1", 1000.0);
        policyData.put("Policy2", 1500.0);
    }

    // Method to get policy amount
    public synchronized double getPolicyAmount(String policyName) {
        return policyData.getOrDefault(policyName, 0.0);
    }

    // Method to update policy amount
    public synchronized void updatePolicyAmount(String policyName, double newAmount) {
        policyData.put(policyName, newAmount);
        System.out.println("Policy " + policyName + " updated. New amount: " + newAmount);
    }

    // Block to perform complex policy operation
    public void performComplexOperation(String policyName) {
        synchronized (this) {
            // Simulate a complex computation by calculating the sum of digits in the policy
            // name
            int sum = calculateSumOfDigits(policyName);

            // For demonstration, we'll just print the policy name and the computed sum
            System.out.println("Complex operation performed on policy: " + policyName + ". Computed sum: " + sum);
        }
    }

    // Helper method to calculate the sum of digits in a string
    private int calculateSumOfDigits(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }
}

class PolicyOperator implements Runnable {
    private PolicyManager policyManager;
    private String policyName;
    private double newAmount;

    public PolicyOperator(PolicyManager policyManager, String policyName, double newAmount) {
        this.policyManager = policyManager;
        this.policyName = policyName;
        this.newAmount = newAmount;
    }

    @Override
    public void run() {
        // Example of accessing synchronized method
        double amount = policyManager.getPolicyAmount(policyName);
        System.out.println("Policy " + policyName + " amount: " + amount);

        // Example of updating policy amount using synchronized method
        policyManager.updatePolicyAmount(policyName, newAmount);

        // Example of accessing synchronized block
        policyManager.performComplexOperation(policyName);
    }
}
