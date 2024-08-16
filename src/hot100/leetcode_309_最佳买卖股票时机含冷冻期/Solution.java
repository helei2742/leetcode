package hot100.leetcode_309_最佳买卖股票时机含冷冻期;

class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len+1][3];
        dp[0][0] = - prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);

            dp[i][1] = dp[i-1][0] + prices[i];

            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]);
        }
        return Math.max(dp[len][1],dp[len][2]);
    }
}
