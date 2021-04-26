package algorithm.blog;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class SelectSort {

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

    public static void main(String[] args) {
        int testTime = 10000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            int[] randomInt = getRandomInt(new Random().nextInt(100) + 10, 1000);
            int[] backup = new int[randomInt.length];
            System.arraycopy(randomInt, 0, backup, 0, backup.length);
            Arrays.sort(backup);
            selectSort(randomInt);
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

    public static void selectSort(int[] data) {
        if (data == null || data.length <= 0) {
            return;
        }

        for (int i = 0; i < data.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) { // 找到最小的值
                minIndex = data[minIndex] > data[j] ? j : minIndex;
            }
            if (i == minIndex) {
                continue;
            }
            swap(data, i, minIndex);
        }
    }

    protected static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
