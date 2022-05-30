package 动态规划.leetcode_518_零钱兑换2;

public class Solution {
    public int change(int amount, int[] coins) {


//        return dfs(coins, 0, 0, amount);

        int [] dp = new int[amount + 1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
    private int dfs(int[] coins, int total, int index, int target){
        if(total == target){
            return 1;
        }
        if(index >= coins.length || total > target) return 0;
        int res = 0;
        res += dfs(coins, total+coins[index], index, target);
        res += dfs(coins, total, index+1, target);
        return res ;
    }

}
