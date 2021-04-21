package algorithm;

import org.apache.commons.codec.BinaryDecoder;

import java.util.Arrays;

/**
 * created 4/21/2021 2:36 PM
 *
 * @author luowen <loovien@163.com>
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 3, 2, 5, 4, 10, 4, 2, 6};

        int distValue = 4;
        // int[] ints = netherlandsFlag(data, 0, data.length - 1, distValue);
        process(data, 0, data.length - 1, distValue);
        System.out.println(Arrays.toString(data));

    }

    private static void process(int[] data, int left, int right, int flag) {
        if (data == null || data.length <= 0 || left >= right) {
            return;
        }
        int[] result = netherlandsFlag(data, left, right);
        if (result[0] <= 0) {
            return;
        }
        process(data, left, result[0] - 1, flag);
        process(data, result[1] + 1, right, flag);
    }

    private static int[] netherlandsFlag(int[] data, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int cursorRight = right, cursorLeft = left - 1, cursor = left;
        while (cursor < cursorRight) {
            if (data[cursor] < data[right]) { // < flag
                int tmp = data[cursor];
                data[cursor] = data[cursorLeft + 1];
                data[cursorLeft + 1] = tmp;
                cursor++;
                cursorLeft++;
            } else if (data[cursor] == data[right]) { // == flag
                cursor++;
            } else { // > flag
                int tmp = data[cursorRight];
                data[cursorRight] = data[cursor];
                data[cursor] = tmp;
                cursorRight--;
            }
        }
        swap(data, cursorRight, right); // why
        return new int[]{cursorLeft + 1, cursorRight};
    }

    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = data[i];
    }
}
