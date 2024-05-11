package JavaConcurrency.src.main.java.com.JavaConcurrency.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }
    }

    public void tryIncrement() {
        if (lock.tryLock()) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean tryDecrement(long timeout, TimeUnit unit) throws InterruptedException {
        if (lock.tryLock(timeout, unit)) {
            try {
                count--;
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public void printCount() {
        System.out.println("Count: " + count);
    }

    public boolean isLockedByCurrentThread() {
        return ((ReentrantLock) lock).isHeldByCurrentThread();
    }

    public boolean isLocked() {
        return ((ReentrantLock) lock).isLocked();
    }

    public int getHoldCount() {
        return ((ReentrantLock) lock).getHoldCount();
    }

    public int getQueueLength() {
        return ((ReentrantLock) lock).getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return ((ReentrantLock) lock).hasQueuedThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample counter = new ReentrantLockExample();

        counter.increment(); // Increment using lock()
        counter.tryIncrement(); // Attempt to increment using tryLock()

        counter.printCount();
        System.out.println("Is locked by current thread? " + counter.isLockedByCurrentThread());
        System.out.println("Is locked? " + counter.isLocked());
        System.out.println("Hold count: " + counter.getHoldCount());
        System.out.println("Queue length: " + counter.getQueueLength());
        System.out.println("Has queued threads? " + counter.hasQueuedThreads());

        Thread.sleep(1000); // Sleep for 1 second

        boolean decremented = counter.tryDecrement(2, TimeUnit.SECONDS); // Attempt to decrement with timeout
        if (decremented) {
            System.out.println("Decrement successful.");
        } else {
            System.out.println("Decrement failed due to timeout.");
        }

        counter.printCount();
    }
}
