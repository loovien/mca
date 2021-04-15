package juc;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        new ThreadPoolExecutor(1, 10, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), (r) -> new Thread(r, "xxx"),
                (r, executor) -> {

                });
    }
}
