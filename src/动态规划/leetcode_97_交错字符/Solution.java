package 动态规划.leetcode_97_交错字符;

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), len = s3.length();
        if(n+m!=len) return false;

        //dp[i][j]表示，s1的前i个 和 s2的前j个可不可以组成 s3的i+j个
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            if(s1.charAt(i) == s3.charAt(i))
                dp[i+1][0] = true;
            else break;
        }
        for (int i = 0; i < m; i++) {
            if(s2.charAt(i) == s3.charAt(i))
                dp[0][i+1] = true;
            else break;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int p = j + i;
                if(s1.charAt(i-1) == s3.charAt(p-1)){
                    dp[i][j] |= dp[i-1][j];
                }
                if(s2.charAt(j-1) == s3.charAt(p-1)){
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }
        return dp[n][m];
    }
}
