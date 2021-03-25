package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    private final static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {

        for (int i = 0; i < 14; i++) {

            new Thread(() -> {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("semaphore ..");
                    System.out.println("========================");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }

    }
}
