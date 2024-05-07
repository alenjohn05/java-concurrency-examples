package JavaConcurrency.src.main.java.com.JavaConcurrency.threads;

public class Main {
    private static double output;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(calculate(500000000));
        Thread Thread1 = new Thread("Thread1") {
            public void run() {
                for (double i = 0; i < 100000000; i++) {
                    Main.output += Math.sqrt(i);
                }
            }
        };
        Thread Thread2 = new Thread("Thread2") {
            public void run() {
                for (double i = 100000000; i < 200000000; i++) {
                    Main.output += Math.sqrt(i);
                }
            }
        };

        Thread Thread3 = new Thread("Thread3") {
            public void run() {
                for (double i = 200000000; i < 300000000; i++) {
                    Main.output += Math.sqrt(i);
                }
            }
        };

        Thread Thread4 = new Thread("Thread4") {
            public void run() {
                for (double i = 30000000; i < 40000000; i++) {
                    Main.output += Math.sqrt(i);
                }
            }
        };

        Thread Thread5 = new Thread("Thread5") {
            public void run() {
                for (double i = 40000000; i < 50000000; i++) {
                    Main.output += Math.sqrt(i);
                }
            }
        };

        long startTime = System.nanoTime();

        Thread1.start();
        Thread2.start();
        Thread3.start();
        Thread4.start();
        Thread5.start();

        try {
            Thread1.join();
            Thread2.join();
            Thread3.join();
            Thread4.join();
            Thread5.join();
        } catch (InterruptedException e) {

        }
        System.out.println(output);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }

    public static double calculate(int number) {
        double output = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            output += Math.sqrt(i);
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        return output;
    }
}