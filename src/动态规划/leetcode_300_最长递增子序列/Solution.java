package 动态规划.leetcode_300_最长递增子序列;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        //dp[i]表示，以i位置结尾，能形成的最长递增子序列的长度
        int[] dp = new int[n];
        dp[1] = 1;
        int res = 0;

        for (int i = 0; i < n; i++) {
            //默认i位置结尾最长递增子序列长度为1
            dp[i] = 1;
            //遍历前面状态转移
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j])
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            res = Math.max(res, dp[i]);
        }
        return res;
    }
}