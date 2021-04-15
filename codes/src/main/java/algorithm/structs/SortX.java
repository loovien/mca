package algorithm.structs;

import java.util.Arrays;

/**
 * @author: luowen <loovien@163.com>
 * @created: 4/15/2021 4:45 PM
 */
public class SortX {

    private static int[] a = new int[]{1, 4, 2, 6, 8, 3, 4, 67};

    public static void main(String[] args) {
        bubbleSort();
        // selectSort();
    }

    public static void bubbleSort() {
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    int tmp;
                    tmp = a[j];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length - i; j++) {
                if (a[i] > a[j]) {
                    int tmp;
                    tmp = a[i];
                    a[j] = a[i];
                    a[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void selectSort() {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp;
                    tmp = a[i];
                    a[j] = a[i];
                    a[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

}
