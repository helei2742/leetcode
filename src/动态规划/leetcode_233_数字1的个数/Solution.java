package 动态规划.leetcode_233_数字1的个数;

import java.util.Arrays;

class Solution {
    char[] bit;
    int[][] dp;
    public int countDigitOne(int n) {
        bit = String.valueOf(n).toCharArray();
        dp = new int[15][15];
        for (int i = 0; i < 15; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dp(0, true, 0);
    }

    private int dp(int idx, boolean isLimit, int cnt) {
        if(idx == bit.length) {
            return cnt;
        }
        if(!isLimit&&dp[idx][cnt] != -1) return dp[idx][cnt];
        int res = 0;
        int up = isLimit?bit[idx]-'0':9;
        for (int i = 0; i <= up; i++) {
            res += dp(idx+1, isLimit&&i==up, i==1?cnt+1:cnt);
        }

        return dp[idx][cnt] = res;
    }
}
