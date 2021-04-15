package juc;

import java.util.concurrent.SynchronousQueue;
import java.util.stream.IntStream;

public class PrintX2 {
    private final static SynchronousQueue<Integer> s1 = new SynchronousQueue<>();
    private final static SynchronousQueue<Character> s2 = new SynchronousQueue<>();

    public static void main(String[] args) {

        new Thread(() -> {

            IntStream.range(0, 26).forEach((v) -> {
                try {
                    s1.put(v);
                    System.out.println(v);
                    s2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }).start();

        new Thread(() -> {
            IntStream.range(0, 26).mapToObj((v) -> (char) (v + 65)).forEach((v) -> {
                try {
                    s1.take();
                    s2.put(v);
                    System.out.println(v);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }).start();

    }
}
