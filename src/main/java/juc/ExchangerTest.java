package juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.LongAdder;

public class ExchangerTest {

    private final static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                String t1_thread = exchanger.exchange("t1 thread");
                System.out.println(Thread.currentThread().getName() + ":" + t1_thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                String t2_thread = exchanger.exchange("t2 thread");
                System.out.println(Thread.currentThread().getName() + ":" + t2_thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
