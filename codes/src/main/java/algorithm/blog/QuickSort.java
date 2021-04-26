package algorithm.blog;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class QuickSort {

    public static void quickSort(int[] data, int left, int right) {
        if (left == right) {
            return;
        }
        int[] middle = partition(data, left, right);
        if (middle[0] < 0) {
            return;
        }
        swap(data, left, (int) (Math.random() * (right - left + 1)));
        quickSort(data, left, middle[0] - 1);
        quickSort(data, middle[1] + 1, right);
    }

    public static int[] partition(int[] data, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, left};
        }
        int p1 = left, p2 = right, cursor = left - 1;
        while (p1 < p2) {
            // 每次和最后一个数据对比
            if (data[p1] == data[right]) { // 如果相等， 左边的数据直接以移
                p1++;
                continue;
            }
            if (data[p1] < data[right]) { //  处理小于的逻辑
                swap(data, p1++, ++cursor);
                continue;
            }
            // 处理大于的逻辑
            swap(data, p1, --p2);
        }
        swap(data, p2, right); // 把数组最后的清数据交换
        return new int[]{cursor + 1, p2};
    }


    public static void main(String[] args) {
        int testTime = 10000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            int[] randomInt = getRandomInt(new Random().nextInt(1) + 10, 1000);
            int[] backup = new int[randomInt.length];
            System.arraycopy(randomInt, 0, backup, 0, backup.length);
            Arrays.sort(backup);
            quickSort(randomInt, 0, randomInt.length - 1);
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
}

