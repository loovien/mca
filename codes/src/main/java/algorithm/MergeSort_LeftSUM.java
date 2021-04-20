package algorithm;

import java.util.Arrays;

public class MergeSort_LeftSUM {

    public static void main(String[] args) {
        // int[] data = {6, 3, 2, 1, 6, 7, 8, 8};
        int[] data = {6, 1, 6};
        int sum = litteSUM(data);
        System.out.println(sum);
        System.out.println(Arrays.toString(data));
    }

    private static int litteSUM(int[] data) {
        if (data == null || data.length <= 1) {
            return (data == null || data.length <= 0) ? -1 : data[0];
        }
        return process(data, 0, data.length - 1);
    }

    private static int process(int[] data, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        int sum1 = process(data, left, middle);
        int sum2 = process(data, middle + 1, right);
        int sum3 = merge(data, left, middle, right);
        return sum1 + sum2 + sum3;
    }

    private static int merge(int[] data, int left, int middle, int right) {
        int sum = 0, cursor = 0;
        int p1 = left, p2 = middle + 1;
        int[] help = new int[right - left + 1];
        while (p1 <= middle && p2 <= right) {
            if (data[p1] < data[p2]) {
                sum += data[p1] * (right - p2 + 1);
                help[cursor++] = data[p1++];
                continue;
            }
            help[cursor++] = data[p2++];
        }

        while (p1 <= middle) {
            help[cursor++] = data[p1++];
        }
        while (p2 <= right) {
            help[cursor++] = data[p2++];
        }
        System.arraycopy(help, 0, data, left, right - left + 1);
        return sum;
    }
}




