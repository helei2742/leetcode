package 动态规划.leetcode_273_完全平方数;

import java.util.Arrays;

public class Solution {
    public int numSquares(int n) {
//        //dp[i]表示i能由完全平方数组成的最小数量
//        int[] dp = new int[n+1];
//        for (int i = 1; i <= n; i++) {
//            int m = Integer.MAX_VALUE;
//            for (int j = 1; j*j <= i; j++) {
//                m = Math.min(dp[i- j * j], m);
//            }
//            dp[i] = m + 1;
//        }
//        return dp[n];
        mem = new int[n+1];
        Arrays.fill(mem, -1);
        return dfs(n);
    }

    private int[] mem;
    private int dfs(int n){
        if(n<=0) return 0;
        if(mem[n] != -1) return mem[n];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i*i <= n ; i++) {
            res = Math.min(dfs(n-i*i), res);
        }
        return mem[n] = res + 1;
    }
}
