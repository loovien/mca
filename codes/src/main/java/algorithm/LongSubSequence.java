package algorithm;

import java.util.HashSet;

/**
 * created 6/5/2021 6:28 AM
 *
 * @author luowen <loovien@163.com>
 */
public class LongSubSequence {
    public static void main(String[] args) {
        String str2 = "abc123";
        String str1 = "xxxcxbxxx3";

        System.out.println(str1);
        System.out.println(str2);

        int result = longSubSeq(str1, str2);
        int result2 = dp(str1.toCharArray(), str2.toCharArray());
        int result4 = process(str1.toCharArray(), str2.toCharArray(), str1.toCharArray().length - 1, str2.toCharArray().length - 1);
        System.out.println(result4 + ":" + result + ":" + result2);
    }

    private static int longSubSeq(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        return process(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1);
    }

    protected static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, str2, i, j - 1);
        }
        if (j == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, str2, i - 1, j);
        }
        int p1 = process(str1, str2, i, j - 1);
        int p2 = process(str1, str2, i - 1, j);
        int p3 = str1[i] == str2[j] ? (1 + process(str1, str2, i - 1, j - 1)) : 0;

        return Math.max(p1, Math.max(p2, p3));

    }

    protected static int dp(char[] str1, char[] str2) {
        int N = str1.length, M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {

                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}
