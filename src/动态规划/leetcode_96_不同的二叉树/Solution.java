package 动态规划.leetcode_96_不同的二叉树;

import java.util.Arrays;

public class Solution {
    public int numTrees(int n) {
//        if(n==0) return 0;
//
//        mem = new int[n+1][n+1];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(mem[i], -1);
//        }
//        return memwork(1, n);
        return dp(n);
    }

    //超时记忆化
    private int work(int start, int end){
        if(start>end) return 1;

        int res = 0;

        for (int i = start; i <= end; i++) {
            int leftCount = work(start, i-1);
            int rightCount = work(i+1, end);

            res += leftCount * rightCount;
        }
        return res;
    }

    private int[][] mem;
    //空间占太多，dp吧
    private int memwork(int start, int end){
        if(mem[start][end] == 0) return mem[start][end];

        if(start>end) return 1;

        int res = 0;

        for (int i = start; i <= end; i++) {
            int leftCount = memwork(start, i-1);
            int rightCount = memwork(i+1, end);

            res += leftCount * rightCount;
        }
        return mem[start][end] = res;
    }

    private int dp(int n){
        int [] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] + dp[i-j];
            }
        }
        return dp[n];
    }

}
