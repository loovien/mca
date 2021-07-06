package juc;

import java.util.Date;
import java.util.concurrent.*;

/**
 * created 7/5/2021 9:59 PM
 *
 * @author luowen <loovien@163.com>
 */
public class ScheduleExecuteTest {

    private final static ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(Integer.toBinaryString(823));

        ses.execute(() -> System.out.println(new Date()));

        ScheduledFuture<?> schedule = ses.schedule(() -> {
            System.out.println("==============");

            return 100;

        }, 0, TimeUnit.SECONDS);

        System.out.println(schedule.get());

        ses.scheduleAtFixedRate(() -> {

            System.out.println("luowen=========");

        }, 0, 1, TimeUnit.SECONDS);


        ScheduledFuture<?> scheduledFuture = ses.scheduleWithFixedDelay(() -> {
            System.out.println("denay ======");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);

        System.out.println(scheduledFuture.get());
    }

}
