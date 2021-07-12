package algo.class06;

import java.util.Arrays;

/**
 * created 5/17/2021 10:11 AM
 *
 * @author luowen <loovien@163.com>
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] data = {1, 5, 2, 3, 4, 6, 7, 9, 8};
        for (int i = 0; i < data.length; i++) {
            heapInsertV1(data, i);
//            heapInsertV2(data, i);
        }
        // O(N)
        for (int i = data.length - 1; i >= 0; i--) {
            heapify(data, i, data.length);
        }
        System.out.println(Arrays.toString(data));
//        int heapSize = data.length;
//        swap(data, 0, --heapSize);
//        while (heapSize > 0) {
//            heapify(data, 0, heapSize);
//            swap(data, 0, --heapSize);
//        }

        int heapSize = data.length;
        swap(data, 0, --heapSize);
        // O(N*logN)
        while (heapSize > 0) { // O(N)
            heapify(data, 0, heapSize); // O(logN)
            swap(data, 0, --heapSize); // O(1)
        }


//        heapSort(data, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    private static void heapSort(int[] data, int length) {
        if (length <= 0) {
            return;
        }
        swap(data, 0, length);
        heapify(data, 0, length - 1);
        heapSort(data, length - 1);

    }

    private static void heapify(int[] data, int i, int size) {
        int left = 2 * i + 1;
        while (left < size) {
            int max = left + 1 < size && data[left + 1] > data[left] ? left + 1 : left;
            max = data[max] > data[i] ? max : i;
            if (i == max) {
                break;
            }
            swap(data, i, max);
            i = max;
            left = i * 2 + 1;
        }
    }

    public static void heapify1(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    private static void heapInsertV2(int[] data, int i) {
        while (data[i] > data[(i - 1) / 2]) {
            swap(data, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void heapInsertV1(int[] data, int i) {
        int prev = (i - 1) / 2;
        if (data[i] > data[prev]) {
            swap(data, i, prev);
            heapInsertV1(data, prev);
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


}
