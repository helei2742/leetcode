package 动态规划.leetcode_396_旋转函数;

public class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            dp[0] += nums[i] * i;
        }


        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + sum - n*nums[n-i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
