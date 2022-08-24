package 剑指Offer二.leetcode_剑指OfferII091_粉刷房子;

import java.util.Arrays;

class Solution {

    public int minCost(int[][] costs) {
        int n = costs.length;
        mem = new int[n][3];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mem[i], -1);
        }
        return dfs(0, -1, costs);
    }

    int[][] mem;
    private int dfs(int idx, int preColor, int[][] costs) {
        if(idx == costs.length) return 0;
        if(preColor!=-1&&mem[idx][preColor] != -1) return mem[idx][preColor];

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if(preColor != i){
                res = Math.min(dfs(idx+1, i, costs) + costs[idx][i], res);
            }
        }
        if (preColor == -1) return res;
        return mem[idx][preColor] = res;
    }

    private int dp(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
        }
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }
}
