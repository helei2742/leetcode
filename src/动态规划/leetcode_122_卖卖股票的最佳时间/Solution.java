package 动态规划.leetcode_122_卖卖股票的最佳时间;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    public int maxProfit(int[] prices) {
//        return dfs(prices, 0, 0);

//        int n = prices.length;
//        mem = new int[n][2];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(mem[i], INF);
//        }
//        return memdfs(prices,0,0);

        return dp(prices);
    }

    private int [][] mem;
    private int INF = Integer.MAX_VALUE-10;

    private int dfs(int [] prices, int current, int isHave){
        if(current == prices.length) return 0;
        int res = dfs(prices, current+1, isHave);

        if(isHave == 1){
            res = Math.max(res, dfs(prices, current+1, 0) + prices[current]);
        }else {
            res = Math.max(res, dfs(prices, current+1, 1) - prices[current]);
        }
        return res;
    }

    private int memdfs(int[] prices, int current, int isHave){
        if(current == prices.length) return 0;
        if(mem[current][isHave] != INF) return mem[current][isHave];
        int res = memdfs(prices, current+1, isHave);

        if(isHave == 1){
            res = Math.max(res, memdfs(prices, current+1, 0) + prices[current]);
        }else {
            res = Math.max(res, memdfs(prices, current+1, 1) - prices[current]);
        }
        return mem[current][isHave] = res;
    }

    private int dp(int[] price) {
        int n = price.length;

        int[][] dp = new int[n][2];
        dp[0][1] = -price[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][1]+price[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-price[i]);
        }
        return dp[n-1][0];
    }
}
