package 剑指Offer二.leetcode_剑指OfferII103_最少的硬币数目;

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[][] dp = new int[len+1][amount+1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE/2);
        }
        for (int i = 0; i <= len; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= coins[i-1]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i-1]] + 1);
                }
            }
        }
        return dp[len][amount] == Integer.MAX_VALUE/2 ? -1:dp[len][amount];
    }
    public int dp(int[] coins, int amount){
        int len = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE/2;
            for (int coin : coins) {
                if(i >= coin){
                    dp[i] = Math.min(dp[i], dp[i - coin]+1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE/2?-1:dp[amount];
    }

}
