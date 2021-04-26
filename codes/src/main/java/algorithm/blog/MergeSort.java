package algorithm.blog;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class MergeSort {

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
            int[] randomInt = getRandomInt(new Random().nextInt(1) + 10, 1000);
            int[] backup = new int[randomInt.length];
            System.arraycopy(randomInt, 0, backup, 0, backup.length);
            Arrays.sort(backup);

            mergeSort(randomInt, 0, randomInt.length - 1);

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

    public static void mergeSort(int[] data, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(data, left, middle);
        mergeSort(data, middle + 1, right);
        merge(data, left, middle, right);
    }

    private static void merge(int[] data, int left, int middle, int right) {
        if (left == right) {
            return;
        }
        int p1 = left, p2 = middle + 1, cursor = 0;
        int[] help = new int[right - left + 1];
        while (p1 <= middle && p2 <= right) { // 对比拷贝数据到help数组中
            help[cursor++] = data[p1] <= data[p2] ? data[p1++] : data[p2++];
        }

        while (p1 <= middle) {
            help[cursor++] = data[p1++];
        }
        while (p2 <= right) {
            help[cursor++] = data[p2++];
        }
        // 将help数组数据拷贝回原数组
        System.arraycopy(help, 0, data, left, right - left + 1);
    }
}
