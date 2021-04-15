package juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentraceLockTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        System.out.println("--------------");
    }
}
