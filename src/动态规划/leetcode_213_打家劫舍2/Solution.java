package 动态规划.leetcode_213_打家劫舍2;

public class Solution {
    public int rob(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }else if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(dp(nums, 0, nums.length - 2),
                dp(nums, 1, nums.length - 1));
    }

    private int dp(int[] nums, int start, int end){
        int first = nums[start];
        int second = Math.max(nums[start], nums[start+1]);

        for (int i = start+2; i <= end ; i++) {
            int temp = Math.max(first + nums[i], second);
            first = second;
            second = temp;
        }
        return second;
    }

    private int dp2(int[] nums, int start, int end){
        int[] dp = new int[nums.length+1];
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[nums.length+1];
    }
}
