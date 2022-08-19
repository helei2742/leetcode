package 剑指offer.leetcode_剑指Offer_63_股票的最大利润;
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n+1][2];
        if(n == 0) return 0;

        dp[1][0] = 0;
        dp[1][1] = -prices[0];

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i-1]);
        }
        return dp[n][0];
    }

    private int other(int[] prices){
        int minPrices = Integer.MAX_VALUE;
        int maxEarn = 0;

        for (int price : prices) {
            if(price < minPrices){
                minPrices = price;
            }else if(price - minPrices > maxEarn){
                maxEarn = price - minPrices;
            }
        }
        return maxEarn;
    }
}