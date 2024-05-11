The `ReentrantLock` class in Java provides several methods for controlling the lock:

1. `lock()`: Acquires the lock. If the lock is not available, the calling thread will block until the lock becomes available.

2. `lockInterruptibly()`: Acquires the lock unless the current thread is interrupted. If the lock is available, the calling thread acquires the lock immediately; otherwise, it waits until the lock becomes available or the thread is interrupted.

3. `tryLock()`: Attempts to acquire the lock without blocking. If the lock is available, the method returns `true` and the calling thread acquires the lock; otherwise, it returns `false` immediately.

4. `tryLock(long time, TimeUnit unit)`: Attempts to acquire the lock within the specified waiting time. If the lock is available before the timeout expires, the method returns `true` and the calling thread acquires the lock; otherwise, it returns `false`.

5. `unlock()`: Releases the lock. If the calling thread holds the lock, the hold count is decremented by one. If the hold count becomes zero, the lock is completely released.

6. `isHeldByCurrentThread()`: Returns `true` if the lock is held by the current thread; otherwise, returns `false`.

7. `isLocked()`: Returns `true` if the lock is held by any thread; otherwise, returns `false`.

8. `getHoldCount()`: Returns the hold count of the lock by the current thread. If the lock is not held by the current thread, returns zero.

9. `getQueueLength()`: Returns an estimate of the number of threads waiting to acquire the lock.

10. `hasQueuedThreads()`: Returns `true` if there are any threads waiting to acquire the lock; otherwise, returns `false`.

These methods provide flexibility and control over acquiring and releasing the lock, as well as querying the lock's status.

Here's an example demonstrating the usage of `ReentrantLock` along with all the mentioned methods in the context of a counter:

```java
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
        return lock.isHeldByCurrentThread();
    }

    public boolean isLocked() {
        return lock.isLocked();
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
```

In this example:

- The `ReentrantLockExample` class demonstrates the usage of `ReentrantLock` with all the mentioned methods.
- The `increment` and `decrement` methods acquire and release the lock using `lock()` and `unlock()` methods, respectively.
- The `tryIncrement` method attempts to acquire the lock using `tryLock()`.
- The `tryDecrement` method attempts to acquire the lock with a specified timeout using `tryLock(long timeout, TimeUnit unit)`.
- The remaining methods demonstrate querying the lock's status: `isLockedByCurrentThread()`, `isLocked()`, `getHoldCount()`, `getQueueLength()`, and `hasQueuedThreads()`.
- The `main` method showcases the usage of all these methods in action.