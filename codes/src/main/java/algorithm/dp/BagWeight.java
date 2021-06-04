package algorithm.dp;

public class BagWeight {
    public static void main(String[] args) {

        int[] p1 = new int[]{2, 1, 1, 1, 2, 1};
        int[] p2 = new int[]{6, 1, 1, 3, 2, 100};

        bagWeight(p1, p2, 3); // 暴力递归
        System.out.println(dp(p1, p2, 3)); // 动态规划

    }

    public static void bagWeight(int[] w, int[] v, int weight) {
        if (w == null || v == null || w.length != v.length || w.length <= 0) {
            throw new RuntimeException("参数异常");
        }
        int value = calc(w, v, 0, weight);
        System.out.println(value);
    }

    public static int calc(int[] w, int[] v, int index, int weight) {
        if (index == w.length) { // 递归到了最后
            return 0;
        }
        int p1 = calc(w, v, index + 1, weight);
        int p2 = 0;
        if (weight - w[index] >= 0) { // 背包没有超重了
            p2 = v[index] + calc(w, v, index + 1, weight - w[index]);
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return -1;
        }
        int length = w.length;
        int[][] dp = new int[length + 1][bag + 1]; // 动态规划表

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                int next = j - w[i] < 0 ? -1 : dp[i + 1][j - w[i]];
                if (next >= 0) {
                    p2 = v[i] + next;
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
