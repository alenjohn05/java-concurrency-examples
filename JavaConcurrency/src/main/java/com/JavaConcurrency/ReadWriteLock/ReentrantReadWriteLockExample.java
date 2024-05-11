package JavaConcurrency.src.main.java.com.JavaConcurrency.ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    private int data = 0;

    public void readData() {
        readLock.lock();
        try {
            System.out.println("Reading data: " + data);
        } finally {
            readLock.unlock();
        }
    }

    public void writeData(int newData) {
        writeLock.lock();
        try {
            System.out.println("Writing data: " + newData);
            data = newData;
        } finally {
            writeLock.unlock();
        }
    }

    public boolean tryWriteData(int newData) {
        if (writeLock.tryLock()) {
            try {
                System.out.println("Writing data: " + newData);
                data = newData;
                return true;
            } finally {
                writeLock.unlock();
            }
        }
        return false;
    }

    public boolean tryReadData() {
        if (readLock.tryLock()) {
            try {
                System.out.println("Reading data: " + data);
                return true;
            } finally {
                readLock.unlock();
            }
        }
        return false;
    }

    public boolean isWriteLocked() {
        return ((ReentrantReadWriteLock) readWriteLock).isWriteLocked();
    }

    public int getReadHoldCount() {
        return ((ReentrantReadWriteLock) readWriteLock).getReadHoldCount();
    }

    public int getWriteHoldCount() {
        return ((ReentrantReadWriteLock) readWriteLock).getWriteHoldCount();
    }

    public int getQueueLength() {
        return ((ReentrantReadWriteLock) readWriteLock).getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return ((ReentrantReadWriteLock) readWriteLock).hasQueuedThreads();
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();

        example.writeData(100); // Writing data

        example.readData(); // Reading data

        boolean success = example.tryWriteData(200); // Trying to write data
        if (success) {
            System.out.println("Write successful.");
        } else {
            System.out.println("Write failed.");
        }

        success = example.tryReadData(); // Trying to read data
        if (success) {
            System.out.println("Read successful.");
        } else {
            System.out.println("Read failed.");
        }

        System.out.println("Is write locked? " + example.isWriteLocked());
        System.out.println("Read hold count: " + example.getReadHoldCount());
        System.out.println("Write hold count: " + example.getWriteHoldCount());
        System.out.println("Queue length: " + example.getQueueLength());
        System.out.println("Has queued threads? " + example.hasQueuedThreads());
    }
}
