package algorithm;

import java.util.Arrays;

public class SelectSort {

    public static void selectSort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        int tmp;
        for (int i = 0; i < data.length; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                min = data[min] > data[j] ? j : min;
            }
            if (min != i) {
                tmp = data[i];
                data[i] = data[min];
                data[min] = tmp;
            }
        }
    }

    public static void print(int[] data) {
        if (data == null || data.length <= 0) {
            return;
        }
        for (int datum : data) {
            System.out.print(datum + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] a = {1, 99, 2, 100, 3, 4, 5, 2, 4, 2, 1, 5, 6, 7, 10};
        print(a);
        selectSort(a);
        print(a);

    }
}
