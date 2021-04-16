package algorithm;

import java.util.Arrays;

public class SBISort {
    private static final int[] data = new int[]{1, 3, 2, 5, 2, 8, 65, 9, 5};

    public static void main(String[] args) {
        // bubbleSort();
        // selectSort();
        insertSort();
    }

    public static void bubbleSort() {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }

    public static void selectSort() {
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }

    public static void insertSort() {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (data[j - 1] > data[j]) {
                    int tmp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
