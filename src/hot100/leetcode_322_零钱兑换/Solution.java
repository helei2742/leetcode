package hot100.leetcode_322_零钱兑换;

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {

        mem = new int[coins.length];
        Arrays.fill(mem, -1);
        int dfs = dfs(coins, amount);
        return dfs == INF ? -1 : dfs;
    }
    private int[] mem;
    private int INF =  Integer.MAX_VALUE>>1;
    private int dfs(int[] coins, int cur) {
        if(cur == 0){
            return 0;
        }
        if(mem[cur ] != -1) return mem[cur];
        int res = INF;
        for (int coin : coins) {
            if(cur >= coin) {
                res = Math.min(res, dfs(coins, cur-coin)+1);
            }
        }
        return mem[cur]=res;
    }

    private int dp(int[] coins, int amount) {
        int[] dp = new int[amount+1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = INF;
            for (int coin : coins) {
                if(i>=coin){
                    dp[i] = Math.min(dp[i-coin]+1, dp[i]);
                }
            }
        }
        return dp[amount] == INF ? -1: dp[amount];
    }
}
