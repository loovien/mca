package algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * created 4/26/2021 9:24 AM
 *
 * @author luowen <loovien@163.com>
 */
public class CountSort {
    public static int[] getRandomInt(int length) {
        return Stream.generate(() -> 1).limit(length).mapToInt((v) -> new Random().nextInt(10000)).toArray();
    }

    public static boolean isEqual(int[] src, int[] dist) {
        for (int i = 0; i < src.length; i++) {
            if (src[i] != dist[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testTimes; i++) {
            int[] randomInt = getRandomInt(10);
            int[] backup = new int[randomInt.length];
            System.arraycopy(randomInt, 0, backup, 0, randomInt.length);
            Arrays.sort(backup);
            // countSortV1(randomInt);
            countSortV2(randomInt);
            if (!isEqual(backup, randomInt)) {
                System.out.println("Oops, fucking fucked..");
                System.out.println(Arrays.toString(backup));
                System.out.println(Arrays.toString(randomInt));
                System.exit(1);
            }
        }
        System.out.println("testing successfully, cost: " + (System.currentTimeMillis() - startTime));
    }

    /**
     * 使用help 数组代替队列
     *
     * @param data
     */
    public static void countSortV2(int[] data) {
        int maxValue = Arrays.stream(data).max().orElse(0);
        if (maxValue <= 0) {
            throw new IllegalArgumentException();
        }
        int digit = 0;
        while (maxValue > 0) {
            maxValue /= 10;
            digit++;
        }
        int[] help = new int[data.length];
        for (int i = 0; i <= digit; i++) {
            int[] count = new int[data.length];
            for (int datum : data) {
                int div = (int) Math.pow(10, i);
                int index = (datum / div) % 10;
                count[index] += 1;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            for (int i1 = data.length - 1; i1 >= 0; i1--) {
                int div = (int) Math.pow(10, i);
                int index = (data[i1] / div) % 10;
                help[count[index] - 1] = data[i1];
                count[index]--;
            }
            System.arraycopy(help, 0, data, 0, data.length);
        }
    }

    public static void countSortV1(int[] data) {
        int maxValue = Arrays.stream(data).max().orElse(0);
        if (maxValue <= 0) {
            throw new IllegalArgumentException();
        }
        int digit = 0;
        while (maxValue > 0) {
            maxValue /= 10;
            digit++;
        }
        LinkedList<Integer>[] bucket = new LinkedList[10];
        for (int i = 0; i <= digit; i++) {
            for (int datum : data) {
                int div = i == 0 ? 1 : (int) Math.pow(10, i);
                int index = (datum / div) % 10;
                if (bucket[index] == null) {
                    bucket[index] = new LinkedList<Integer>();
                }
                bucket[index].add(datum);
            }

            int cursor = 0;
            for (int j = 0; j < 10; j++) {
                while (bucket[j] != null && !bucket[j].isEmpty()) {
                    data[cursor++] = bucket[j].poll();
                }
            }
        }

    }
}
