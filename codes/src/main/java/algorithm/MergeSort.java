package algorithm;

import java.util.Arrays;

/**
 * created 4/20/2021 1:58 PM
 *
 * @author luowen <loovien@163.com>
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] data = {1, 4, 2, 5, 3, 232, 54, 454, 23, 23, 54, 6, 7, 4324, 0, -1};
        mergeSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    private static void mergeSort(int[] data, int left, int right) {
        if (left == right) {
            return;
        }
        int midden = left + ((right - left) >> 1);
        mergeSort(data, left, midden);
        mergeSort(data, midden + 1, right);
        merge(data, left, midden, right);
    }

    private static void merge(int[] data, int left, int midden, int right) {
        int p1 = left, p2 = midden + 1;
        int cursor = 0;
        int[] help = new int[right - left + 1];

        while (p1 <= midden && p2 <= right) {
            if (data[p1] >= data[p2]) {
                help[cursor++] = data[p2++];
                continue;
            }
            help[cursor++] = data[p1++];
        }

        while (p1 <= midden) {
            help[cursor++] = data[p1++];
        }

        while (p2 <= right) {
            help[cursor++] = data[p2++];
        }
        System.arraycopy(help, 0, data, left, right - left + 1);
    }

}
