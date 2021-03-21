package algorithm;

import java.util.Arrays;

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
        int[] a = {1, 4, 2, 5, 6, 2, 5, 109923, 3, 1, 24, 45};
        Arrays.stream(a).forEach((v) -> System.out.print(v + " "));
        System.out.println();
        sort(a);
        Arrays.stream(a).forEach((v) -> System.out.print(v + " "));
    }

}
