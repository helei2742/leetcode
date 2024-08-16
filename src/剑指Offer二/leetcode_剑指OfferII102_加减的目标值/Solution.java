package 剑指Offer二.leetcode_剑指OfferII102_加减的目标值;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if((sum - target) %2 != 0 || sum-target < 0) return 0;
        int t = (sum-target)/2;

        int[][] dp = new int[len+1][t+1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= nums[i-1]){
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[len][t];
//        return dfs(nums, 0,0,target);
    }
    private int dfs(int[] nums, int idx, int cur,int target) {
        if(idx >= nums.length) {
            if(cur == target) return 1;
            return 0;
        }
        int res = 0;
        res += dfs(nums, idx+1, cur + nums[idx], target);
        res += dfs(nums, idx+1, cur - nums[idx], target);
        return res;
    }
}
