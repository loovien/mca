package algorithm.dp;

public class Conv2Str {
    public static void main(String[] args) {

        String s = "12121";
        System.out.println(conv(s.toCharArray(), 0));
        System.out.println(dp(s.toCharArray()));


    }

    public static int conv(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }
        // 单转
        int conv1 = conv(chars, index + 1); // 合下个数一起转
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') < 27) {
            conv1 += conv(chars, index + 2);
        }
        return conv1;
    }

    public static int dp(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int length = chars.length;
        int[] dp = new int[length + 1];
        dp[length] = 1;
        for (int i = length - 1; i >= 0; i--) {
            int cnt = dp[i + 1];
            if (i + 1 < length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
                cnt += conv(chars, i + 2);
            }
            dp[i] = cnt;
        }
        return dp[0];
    }
}
