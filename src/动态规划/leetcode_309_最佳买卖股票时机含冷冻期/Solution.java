package 动态规划.leetcode_309_最佳买卖股票时机含冷冻期;

public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //dp[i][0] 代表在i天有股票，时的累计最大收益
        //dp[i][1] 代表在i天没股票，并且在冷冻其的最大收益
        //dp[i][2] 代表在i天没股票，不在冷冻期的最大收益
        int[][] dp = new int[n][3];

        dp[0][0] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }
        return Math.max(dp[n-1][1], dp[n-1][2]);
    }
}
