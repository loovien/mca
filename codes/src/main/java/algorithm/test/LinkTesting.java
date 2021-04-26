package algorithm.test;

import java.util.Arrays;

/**
 * created 4/26/2021 3:33 PM
 *
 * @author luowen <loovien@163.com>
 */
public class LinkTesting {
    public static class Node {
        Integer value;
        Node prev;
        Node next;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 5, 7, 5, 234, 2, 3};
        quickSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public static void quickSort(int[] data, int left, int right) {
        if (data == null || data.length <= 0) {
            return;
        }
        if (left >= right) {
            return;
        }
        int[] position = partition(data, left, right);
        quickSort(data, left, position[0] - 1);
        quickSort(data, position[1] + 1, right);
    }

    private static int[] partition(int[] data, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int p1 = left, p2 = right, cursor = left - 1;
        while (p1 < p2) {
            if (data[p1] == data[right]) {
                p1++;
                continue;
            }
            if (data[p1] < data[right]) {
                swap(data, p1++, ++cursor);
                continue;
            }
            swap(data, p1, --p2);
        }
        swap(data, p2, right);
        return new int[]{cursor + 1, p2};
    }

    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
