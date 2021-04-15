package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

    protected final static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("ring"));

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("ending");
    }
}
