package algorithm.test;

import java.util.HashMap;

/**
 * created 4/20/2021 12:58 PM
 *
 * @author luowen <loovien@163.com>
 */
public class MaxNumRecurise {

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 100, 4, 13, 34, 22, 45, 34};


        System.out.println(maxNum(a, 0, a.length - 1));
    }

    public static int maxNum(int[] data, int left, int right) {
        if (data == null || data.length <= 1) {
            return data == null ? -1 : data[0];
        }
        if (left == right) {
            return data[left];
        }
        int middle = left + ((right + left) >> 1);
        int leftMax = maxNum(data, left, middle);
        int rightMax = maxNum(data, middle + 1, right);
        return Math.max(leftMax, rightMax);
    }
}
