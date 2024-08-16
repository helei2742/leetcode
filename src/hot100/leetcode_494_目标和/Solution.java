package hot100.leetcode_494_目标和;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        //设添加负号的数的和为n  则添加加号的元素和为sum-n
        //则有 sum-n - n = target
        //n = sum-target/2
        //就相对于求和为n的方案数
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if(sum-target < 0 || (sum-target)%2 != 0) return 0;

        int t = (sum-target)/2;
        int[][] dp = new int[len+1][t+1];

        dp[0][0] = 1;

        for (int i = 1; i <= len; i++) {
            int num = nums[i];
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= num) {
                    dp[i][j] += dp[i-1][j-num];
                }
            }
        }
        return dp[len][t];

//        return dfs(nums, 0, 0, target);
    }
    private int dfs(int[] nums, int idx, int sum, int target) {
        if(idx == nums.length){
            if(sum == target) return 1;
            return 0;
        }
        int res = 0;
        res += dfs(nums, idx+1, sum+nums[idx], target);
        res += dfs(nums, idx+1, sum-nums[idx], target);
        return res;
    }
}
