package containers;

import java.util.ArrayList;
import java.util.List;

public class TickerSellerTest {

    private static final List<String> tickets = new ArrayList<>();

    private static final int max = 100;

    static {
        for (int corsor = 0; corsor < max; corsor++) {
            tickets.add("ticket_no: " + corsor);
        }
    }

    public static void main(String[] args) {
        // IntStream.range(65, 91).mapToObj((v) -> (char) v).forEach(System.out::println);
    }

    private static void consume() {
        int chuck = max / 10;
        for (int threadNo = 0; threadNo < 10; threadNo++) {
            int offset = threadNo * chuck;
            new Thread(() -> {
                for (int i = 0; i < chuck; i++) {
                    System.out.println(Thread.currentThread().getName() + " consume: " + tickets.get(offset + i));
                    // tickets.remove(offset);
                }
            }, "thread-" + threadNo).start();
        }
    }
}
