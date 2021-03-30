package test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueTest {
    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " queue size: " + linkedBlockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-1").start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " queue size: " + linkedBlockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-2").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                linkedBlockingQueue.add("thread" + i);
                System.out.println("============================================");
            }
        }).start();
    }
}
