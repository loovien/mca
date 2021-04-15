package algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class BubbleSort {

    private static void sort(int[] data) {
        int tmp;
        for (int i = data.length - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (data[i] < data[j]) {
                    tmp = data[j];
                    data[j] = data[i];
                    data[i] = tmp;
                }
            }
        }
    }


    public static void main(String[] args) {

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        for (int i = 0; i < 1; i++) {
            int[] ints = lenRandomValueRandom(10, 40);
            int[] ints1 = copyArray(ints);
            sort(ints);
            System.out.println(Arrays.toString(ints1));
            System.out.println(Arrays.toString(ints));
        }

    }

    public static int[] copyArray(int[] a) {
        int[] dist = new int[a.length];
        System.arraycopy(a, 0, dist, 0, a.length);
        return dist;
    }

    public static int[] lenRandomValueRandom(int len, int value) {
        Random random = new Random();
        return Stream.generate(() -> 0).limit(random.nextInt(len)).mapToInt((v) -> random.nextInt(value)).toArray();
    }
}