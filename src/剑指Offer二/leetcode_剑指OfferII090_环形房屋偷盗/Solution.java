package 剑指Offer二.leetcode_剑指OfferII090_环形房屋偷盗;

class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        if(len == 2) return Math.max(nums[0], nums[1]);

        int dp1 = dp(nums, 0, nums.length - 2);
        int dp2 = dp(nums, 1, nums.length - 1);
        return Math.max(dp1, dp2);
    }

    private int dp(int[] nums, int start, int end) {
/*        int[] dp = new int[end - start + 2];
        dp[start] = nums[start];
        dp[start+1] = Math.max(nums[start], nums[start+1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[end];*/
        int dp1 = nums[start], dp2 =  Math.max(nums[start], nums[start+1]), dp3 = 0;

        for (int i = start + 2; i <= end; i++) {
            dp3 = Math.max(dp2, dp1 + nums[i]);
            dp1 = dp2;
            dp2 = dp3;
        }
        return dp3;
    }
}
