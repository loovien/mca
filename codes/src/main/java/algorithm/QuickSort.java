package algorithm;

import org.apache.commons.codec.BinaryDecoder;
import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * created 4/21/2021 2:36 PM
 *
 * @author luowen <loovien@163.com>
 */
public class QuickSort {
    private final static Unsafe unsafe = Unsafe.getUnsafe();

    public static void main(String[] args) {
        int[] data = {1, 2, 12, 4, 3, -1, 2, 5, 4, 10, 4, 2, 6};
        process(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    private static void process(int[] data, int left, int right) {
        if (left > right) {
            return;
        }
        swap(data, left + (int) (Math.random() * (right - left + 1)), right);
        int[] offset = netherlandFlags(data, left, right);
        if (offset[0] < 0) {
            return;
        }
        process(data, left, offset[0] - 1);
        process(data, offset[1] + 1, right);
    }

    private static int[] netherlandFlags(int[] data, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int less = left - 1, index = left, more = right;

        while (index < more) {
            if (data[index] > data[right]) {
                swap(data, index, --more);
            } else if (data[index] == data[right]) {
                index++;
            } else {
                swap(data, index++, ++less);
            }
        }
        swap(data, index, right);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


}
