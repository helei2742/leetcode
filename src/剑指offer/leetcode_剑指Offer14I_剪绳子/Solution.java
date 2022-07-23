package 剑指offer.leetcode_剑指Offer14I_剪绳子;

import java.util.Arrays;

class Solution {
    public int cuttingRope(int n) {
//        mem =new int[55];
//        Arrays.fill(mem, -1);
//
//        return dfs(n);
        return dp(n);
    }

    int[] mem;
    private int dfs(int n){
        if(n == 1) return 1;
        if(mem[n] != -1) return mem[n];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int x = Math.max(dfs(n-i) * i,  (n-i)*i);
            res = Math.max(res, x);
        }
        return mem[n]=res;
    }

    private int dp(int n){
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int t = Math.max(j*(i-j), j*dp[i-j]);
                dp[i] = Math.max(t, dp[i]);
            }
        }
        return dp[n];
    }
}