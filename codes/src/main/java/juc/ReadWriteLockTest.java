package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    private static final Lock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    protected static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void write(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("write ..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < 18; i++) {
            // new Thread(() -> read(readWriteLock.readLock())).start();
            new Thread(() -> read(lock)).start();
        }
        for (int i = 0; i < 2; i++) {
            // new Thread(() -> write(readWriteLock.writeLock())).start();
            new Thread(() -> write(lock)).start();
        }
    }

}
