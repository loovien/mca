package algorithm.blog;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class BubbleSort {
    public static int[] getRandomInt(int length, int maxValue) {
        Random random = new Random();
        return Stream.generate(() -> 1).limit(length).mapToInt((v) -> random.nextInt(maxValue)).toArray();
    }

    public static boolean isEquals(int[] src, int[] dist) {
        for (int i = 0; i < src.length; i++) {
            if (src[i] != dist[i]) {
                return false;
            }
        }
        return true;
    }

    protected static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    public static void main(String[] args) {
        int testTime = 10000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            int[] randomInt = getRandomInt(new Random().nextInt(100) + 10, 1000);
            int[] backup = new int[randomInt.length];
            System.arraycopy(randomInt, 0, backup, 0, backup.length);
            Arrays.sort(backup);
            bubbleSort(randomInt);
            if (!isEquals(backup, randomInt)) {
                System.out.println("Oops invalid...");
                System.out.println(Arrays.toString(randomInt));
                System.out.println(Arrays.toString(backup));
                System.out.println("====================");
                System.exit(0);
            }
        }
        System.out.println("successfully!! cost: " + (System.currentTimeMillis() - startTime));
    }

    public static void bubbleSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data.length - i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }
}
