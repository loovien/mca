package juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

class Bucket {

    private final static Object[] data = new Object[10];

    private volatile static int cursor = 0;


    public static <T> void add(T t) throws Exception {
        if (cursor == 10) {
            throw new Exception("max 10");
        }
        data[cursor] = t;
    }

}

public class ContainerTest {

    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Bucket.add(i);
                    System.out.println(i);
                    if (i == 5) {
                        exchanger.exchange(i);
                        exchanger.exchange(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            try {
                exchanger.exchange(1);
                System.out.println("end");
                exchanger.exchange(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
