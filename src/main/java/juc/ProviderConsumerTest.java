package juc;

import java.util.LinkedList;

public class ProviderConsumerTest {

    private static class Container<E> {
        LinkedList<E> list = new LinkedList<>();
        private static final int max = 10;

        public synchronized void provider(E e) throws InterruptedException {
            while (list.size() == max) {
                System.out.println("provider enough.");
                this.wait();
            }
            list.add(e);
            this.notifyAll();
        }

        public synchronized void consume() throws InterruptedException {
            while (list.size() == 0) {
                System.out.println("consumer enough!");
                this.wait();
            }
            list.removeFirst();
            this.notifyAll();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Container<String> stringContainer = new Container<>();

        for (int i = 0; i < 8; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    stringContainer.provider(String.valueOf(finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    stringContainer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
