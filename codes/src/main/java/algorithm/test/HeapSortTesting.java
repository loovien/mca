package algorithm.test;

import java.util.Arrays;

/**
 * created 4/23/2021 9:55 AM
 *
 * @author luowen <loovien@163.com>
 */
public class HeapSortTesting {

    public static void main(String[] args) {
        int[] data = {1, 5, 3, 21, 65, -1, 3, 5, 7, 5, 2456, 7, 5657, 9};
        // heapInsertSort(data);
        heapSortUseIfy(data);
        System.out.println(Arrays.toString(data));
    }

    private static void heapInsertSort(int[] data) {
        int heapSize = data.length;
        for (int i = 0; i < data.length; i++) {
            heapInsert(data, i);
        }
        swap(data, 0, --heapSize);
        while (heapSize > 0) {
            for (int i = 0; i < heapSize; i++) {
                heapInsert(data, i);
            }
            swap(data, 0, --heapSize);
        }
    }

    private static void heapSortUseIfy(int[] data) {
        int heapSize = data.length;
        for (int i = heapSize - 1; i >= 0; i--) {
            heapIfy(data, i, heapSize - 1);
        }
        swap(data, 0, --heapSize);
        while (heapSize > 0) {
            for (int i = 0; i < heapSize; i++) {
                heapIfy(data, i, heapSize);
            }
            swap(data, 0, --heapSize);
        }
    }

    private static void heapIfy(int[] data, int i, int heapSize) {
        int left = i * 2 + 1;
        while (left < heapSize) {
            int largest = (left + 1 < heapSize && data[left + 1] > data[left]) ? left + 1 : left;
            if (data[i] < data[largest]) {
                swap(data, i, largest);
            }
            i = left;
            left = left * 2 + 1;
        }
    }

    private static void heapInsert(int[] data, int i) {
        int prev = (i - 1) / 2;
        while (prev >= 0) {
            if (data[i] > data[prev]) {
                swap(data, i, prev);
            }
            if (prev == 0) {
                break;
            }
            i = prev;
            prev = (prev - 1) / 2;
        }
        /*while (data[i] > data[(i - 1) / 2]) {
            swap(data, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }*/
    }


    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


}
