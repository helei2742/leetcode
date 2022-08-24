package 剑指Offer二.leetcode_剑指OfferII088_爬楼梯的最少成本;

import java.util.Arrays;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
/*        int len = cost.length;
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[len];
        */

        mem = new int[cost.length+1];
        Arrays.fill(mem, -1);
        return dfs(cost, cost.length);
    }

    private int[] mem;
    private int dfs(int[] cost, int idx) {
        if(idx <= 1) return 0;
        if(mem[idx] != -1) return mem[idx];
        int res = 0;
        int l = dfs(cost, idx - 1) + cost[idx-1];
        int r = dfs(cost, idx - 2) + cost[idx-2];
        res += Math.min(l, r);
        return mem[idx] = res;
    }
}
