package 动态规划.leetcode_121_买卖股票的最佳时机;

public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //dp[i][j]表示在i天是否持有股票 （j=0表示未持有，1表示持有） 的收益
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            //i天没有股票有两种情况，一是前面也没有股票，或者前面有股票，i天卖了
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            //i天有股票也有两种情况，一是前面有股票，或者前面没股票今天买了,
            dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
        }
        return dp[n-1][0];
    }

}
