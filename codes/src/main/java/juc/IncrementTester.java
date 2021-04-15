package juc;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class IncrementTester {

    static int a = 0;

    static AtomicInteger a1 = new AtomicInteger(0);

    static LongAdder a2 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Instant now = Instant.now();
        System.out.println(now.getLong(ChronoField.INSTANT_SECONDS));
        // longAddr();
        // atomicLock();
        syncLock();
        Instant now1 = Instant.now();
        System.out.println("longAddr: " + ChronoUnit.MILLIS.between(now, now1));
//        atomicLock();
//        Instant now2 = Instant.now();
//        System.out.println("atomic: " + ChronoUnit.MILLIS.between(now2, now1));
//        syncLock();
//        Instant now3 = Instant.now();
//        System.out.println("sync: " + ChronoUnit.MILLIS.between(now3, now2));

    }

    protected static void longAddr() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1_000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    a2.add(1);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(a2.intValue());
    }

    protected static void atomicLock() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    a1.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(a1.get());
    }

    protected static void syncLock() throws InterruptedException {
        final Object lock = new Object();
        Thread[] arr = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                synchronized (lock) {
                    for (int j = 0; j < 100000; j++) {
                        a = a + 1;
                    }
                }
            });
            thread.start();
            arr[i] = thread;
        }
        Arrays.stream(arr).forEach((v) -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(a);
    }
}
