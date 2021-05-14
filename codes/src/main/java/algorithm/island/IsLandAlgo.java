package algorithm.island;

import java.util.Arrays;

/**
 * created 5/14/2021 11:05 AM
 *
 * @author luowen <loovien@163.com>
 */
public class IsLandAlgo {


    public static int findCircleNum(int[][] data) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    result++;
                    infect(data, i, j);
                }
            }
        }
        return result;
    }

    /**
     * 感染问题
     *
     * @param data data structure
     * @param i    index row
     * @param j    index column
     */
    private static void infect(int[][] data, int i, int j) {
        if (i < 0 || i >= data.length || j < 0 || j >= data[0].length || data[i][j] != 1) {
            return;
        }
        data[i][j] = 2;
        infect(data, i - 1, j);
        infect(data, i + 1, j);
        infect(data, i, j - 1);
        infect(data, i, j + 1);
    }

    public static void main(String[] args) {
        int[][] data = new int[5][5];
        data[0][0] = 1;
        data[0][1] = 1;
        data[0][3] = 1;
        data[1][1] = 1;
        data[1][2] = 1;
        data[2][2] = 1;

        data[0][4] = 1;
        data[1][4] = 1;
        data[2][4] = 1;
        data[3][4] = 1;

        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }

        int circleNum = findCircleNum(data);
        System.out.println(circleNum);

        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
    }
}
