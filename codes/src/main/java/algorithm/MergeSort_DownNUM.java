package algorithm;

import java.util.Arrays;

public class MergeSort_DownNUM {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 3,     4};
        int result = downCnt(data, 0, data.length - 1);
        System.out.println(result);
        System.out.println(Arrays.toString(data));
    }

    private static int downCnt(int[] data, int left, int right) {
        if (data == null || data.length <= 1) {
            return 0;
        }
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);

        return downCnt(data, left, middle) +
                downCnt(data, middle + 1, right) +
                merge(data, left, middle, right);
    }

    private static int merge(int[] data, int left, int middle, int right) {
        int p1 = middle, p2 = right, cursor = right - left, result = 0;
        int[] help = new int[right - left + 1];

        while (p1 >= left && p2 >= middle + 1) {
            if (data[p1] > data[p2]) {
                result += p2 - middle;
                help[cursor--] = data[p2--];
            }
            help[cursor--] = data[p1--];
        }

        while (p1 >= left) {
            help[cursor--] = data[p1--];
        }

        while (p2 >= middle + 1) {
            help[cursor--] = data[p2--];
        }
        System.arraycopy(help, 0, data, left, right - left + 1);
        return result;
    }
}
