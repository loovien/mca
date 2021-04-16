package algorithm;

import lombok.var;

import java.util.Arrays;

/**
 * @author: luowen <loovien@163.com>
 * @created: 4/15/2021 6:15 PM
 */
public class BinarySearch {
    private static int[] data = new int[]{1, 2, 3, 2, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        /*Arrays.stream(data).forEach(v -> {
            System.out.println(binarySearchV2(data, v));
        });*/
        // System.out.println(binarySearchV2(data, 1));
        // System.out.println(findLeft(data, 3));
        System.out.println(smallerRange(data));
    }

    public static boolean binarySearchV1(int[] data, int num) {
        int index = data.length / 2;
        if (data[index] == num) {
            return true;
        }
        if (index <= 0) {
            return false;
        }
        int[] segment = new int[index];
        if (data[index] > num) {
            System.arraycopy(data, 0, segment, 0, index);
        } else {
            System.arraycopy(data, index, segment, 0, index);
        }
        return binarySearchV1(segment, num);
    }

    public static boolean binarySearchV2(int[] data, int search) {
        if (data == null || data.length == 0) {
            return false;
        }
        int L = 0, R = data.length - 1;
        while (L <= R) {
            int middle = (L + R) / 2;
            if (data[middle] == search) {
                return true;
            }
            if (data[middle] > search) {
                R = middle - 1;
            } else {
                L = middle + 1;
            }
        }
        return false;
    }

    public static int findLeft(int[] data, int search) {
        if (data == null || data.length == 0) {
            return -1;
        }

        int L = 0, R = data.length - 1, result = -1;
        while (L <= R) {
            int middle = (L + R) / 2;
            /*if (data[middle] == search) {
                result = middle;
                R = middle - 1;
                continue;
            }*/
            if (data[middle] > search) {
                // R = middle - 1;
                R = middle - 1;
            } else if (data[middle] <= search) {
                result = middle;
                L = middle + 1;
            }
        }
        return result;
    }

    public static int smallerRange(int[] data) {
        if (data == null || data.length == 0) {
            return -1;
        }
        if (data[0] < data[1]) {
            return data[0];
        }
        if (data[data.length - 2] > data[data.length - 1]) {
            return data[data.length - 1];
        }
        int L = 0, R = data.length - 1;
        while (L <= R) {
            int middle = (L + R) / 2;
            if (data[middle] < data[middle + 1] && data[middle] < data[middle - 1]) {
                return data[middle];
            }
            if (data[middle] > data[middle - 1]) {
                R = middle - 1;
                continue;
            }
            if (data[middle] > data[middle + 1]) {
                L = middle + 1;
            }
        }
        return -1;
    }

}
