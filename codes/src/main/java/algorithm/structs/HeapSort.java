package algorithm.structs;

import java.util.Arrays;

/**
 * created 4/22/2021 2:02 PM
 *
 * @author luowen <loovien@163.com>
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] data = {-100, 1, 3, 2, 56, 7, 8, 3, 5, 7, 9, 0, 4};
        // heapSortUseInsert(data);
        heapSortUseInsert(data);
        System.out.println(Arrays.toString(data));
    }

    private static void heapSortUseInsert(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            heapInsert(data, i);
        }
        int index = data.length - 1;
        while (index >= 0) {
            swap(data, 0, index);
            for (int i = 0; i < index; i++) {
                heapInsert(data, i);
            }
            index--;
        }
    }

    /**
     * 使用下沉的方式
     *
     * @param data int[] data
     */
    private static void heapSortUseIfy(int[] data) {
        int heapSize = data.length - 1, index = 0;
        for (int i = data.length - 1; i > 0; i++) { // 找到堆的最大值
            heapIfy(data, i, heapSize);
        }
        swap(data, index, --heapSize); // 交换后， 就只有比对交换后的值了
        while (heapSize > 0) {
            heapIfy(data, index, heapSize);
            swap(data, 0, --heapSize);
        }
    }

    public static void heapIfy(int[] data, int index, int heapSize) {
        int left = 2 * index + 1; // 当前节点的左子节点索引是 2 * index + 1, 右子节点是 2*index + 2
        while (left < heapSize) {
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = (left + 1 < heapSize && data[left + 1] > data[left]) ? left + 1 : left;
            largest = data[largest] > data[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(data, largest, index);
            index = largest;
            left = 2 * index + 1;
        }

    }

    public static void heapInsert(int[] data, int i) {
        while (data[i] > data[(i - 1) / 2]) {
            swap(data, i, ((i - 1) / 2));
            i = (i - 1) / 2;
        }
    }


    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
