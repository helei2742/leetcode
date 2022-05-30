package 动态规划.leetcode_322_零钱兑换;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int coinChange(int[] coins, int amount) {
/*        mem = new HashMap<>();

        return dfs(coins, amount);*/
        return dp(coins, amount);
    }

    private Map<Integer, Integer> mem;
    private int dfs(int[] coins, int amount){
        if(amount == 0) return 0;
        if(mem.containsKey(amount)) return mem.get(amount);

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if(amount >= coins[i]){
                int t = dfs(coins, amount - coins[i]);
                if(t >= 0 && t < res){
                    res = t;
                }
            }
        }
        res = res == Integer.MAX_VALUE?-1:res+1;
        mem.put(amount, res);
        return res;
    }

    private int dp(int[] coins, int amount){

        //dp[i]表示， i这么多前需要用的最少银币数
        int[] dp = new int[amount+1];
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE/2;
            for (int coin : coins) {
                if(i>=coin){
                    dp[i] = Math.min(dp[i-coin]+1,dp[i]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE/2?-1:dp[amount];
    }
}
