package algorithm;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] data = new int[]{1, 4, 2, 6, 4, 7, 2, 4, 9, 8};
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int tmp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
