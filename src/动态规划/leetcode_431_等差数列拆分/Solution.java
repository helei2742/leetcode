package 动态规划.leetcode_431_等差数列拆分;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
//        dfs(nums, nums.length-1);
//
//        return ans;
        return dp(nums);
    }

    private int ans = 0;
    private int dfs(int[] nums, int end){
        if(end < 2) return 0;

        int res = 0;

        if(nums[end] - nums[end-1] == nums[end-1] - nums[end-2]){
            res = dfs(nums, end-1)+1;
            ans += res;
        }else {
            dfs(nums,end-1);
        }
        return res;
    }

    private int dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];

        int ans = 0;
        for (int i = 2; i < n; i++) {
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                dp[i] = dp[i-1] + 1;
            }
            ans += dp[i];
        }
        return ans;
    }
}
