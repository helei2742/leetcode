package 动态规划.leetcode_198_打家劫舍;

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        if (nums.length ==0) return 0;
        if (nums.length ==1) return nums[0];
//        int[] memo = new int[nums.length];
//        Arrays.fill(memo,-1);
//        return  robHouse(nums,nums.length-1,memo);
        return dp2(nums);
    }

    private int robHouse(int[] nums, int k, int[] memo) {
        if (k<0) return  0;

        if (memo[k]!=-1) return  memo[k];
        return memo[k] =Math.max(robHouse(nums,k-1,memo),
                nums[k]+robHouse(nums,k-2,memo));
    }

    private int dp(int[] nums){
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
           dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[nums.length-1];
    }
    private int dp2(int[] nums){

        int x = nums[0];
        int y = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(nums[i] + x, y);
            x = y;
            y = temp;
        }
        return y;
    }
}
