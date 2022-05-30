package 动态规划.leetcode_377_组数总和;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public int combinationSum4(int[] nums, int target) {
/*
        int n = nums.length;
        mem = new int[target+1];
        Arrays.fill(mem, -1);
        return dfs(nums,target);*/

        return dp(nums,target);
    }

    private int[] mem;
    private int dfs(int[] nums, int target){
        if(target == 0) return 1;
        if(mem[target]!=-1) return mem[target];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] <= target){
                res += dfs(nums, target - nums[i]);
            }
        }
        return mem[target] = res;
    }

    private int dp(int[] nums, int target) {
        int[] dp = new int[target+1];
        int n = nums.length;
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {

            for (int j = 0; j < nums.length; j++) {
                if(i>=nums[j]){
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}
