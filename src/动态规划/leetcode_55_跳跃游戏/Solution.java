package 动态规划.leetcode_55_跳跃游戏;

public class Solution {
    public boolean canJump(int[] nums) {
        if(nums==null||nums.length<1) return true;

        int n = nums.length;
        boolean [] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if(dp[j]&&nums[j]>=i-j){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n-1];
    }

    //贪心
    public boolean fn(int[] nums){
        if(nums==null||nums.length<1) return true;

        int rightmost = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if(i <= rightmost){
                rightmost = Math.max(rightmost, i + nums[i]);
                if(rightmost >= n){
                    return true;
                }
            }
        }
        return false;
    }
}
