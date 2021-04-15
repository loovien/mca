package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (i == 3) {
                        LockSupport.park();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(4);
        System.out.println("after 4 seconds");
        LockSupport.unpark(thread);
    }
}
