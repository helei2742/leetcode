package 剑指Offer二.leetcode_剑指OfferII101_分割等和子集;

class Solution {
    public boolean canPartition(int[] nums) {
        int tot = 0, max = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            tot += nums[i];
            max = Math.max(max, nums[i]);
        }

        if(tot % 2 != 0) return false;
        int target = tot/2;
        if(max > target) return false;

        boolean[][] dp = new boolean[len][target+1];
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= target; j++) {
                if(j >= nums[i]) {
                    dp[i][j] = dp[i-1][j] | dp[i-1][j - nums[i]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len-1][target];
    }
}
