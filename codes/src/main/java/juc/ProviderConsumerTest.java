package juc;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProviderConsumerTest {

    private static class Container<E> {
        LinkedList<E> list = new LinkedList<>();
        private static final int max = 10;

        public synchronized void provider(E e) throws InterruptedException {
            while (list.size() == max) {
                System.out.println(Thread.currentThread().getName() + " : provider enough.");
                TimeUnit.SECONDS.sleep(2);
                this.wait();
            }
            list.add(e);
//            TimeUnit.SECONDS.sleep(1);
            this.notifyAll();
        }

        public synchronized void consume() throws InterruptedException {
            while (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " : consumer over");
                TimeUnit.SECONDS.sleep(2);
                this.wait();
            }
            E e = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + " : consumer: " + e);
            this.notifyAll();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Container<String> stringContainer = new Container<>();

        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    while (true) {
                        stringContainer.provider(String.valueOf(finalI + random.nextInt()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        new Thread(() -> {
            try {
                while (true) {
                    stringContainer.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("===============");

    }
}
