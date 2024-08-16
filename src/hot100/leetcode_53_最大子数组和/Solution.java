package hot100.leetcode_53_最大子数组和;

class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] + Math.max(dp[i-1], 0);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
