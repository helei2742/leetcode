package 动态规划.leetcode_45_跳跃游戏;


import java.util.Map;

class Solution {

    public int jump(int[] nums) {
        int n = nums.length;
        if(n<=1) return 0;
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
            dp[i] = 10001;
            for (int j = 0; j < i; j++) {
                if(nums[j] >= i - j){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n-1];
    }

    private Map<Integer,Integer> mem;
    private int dfs(int [] nums, int index){
        if(index >= nums.length) return 0;
        if(mem.containsKey(index)) return mem.get(index);
        int res = nums.length;
        for (int i = 1; i <= nums[i]; i++) {
            res += Math.min(res, dfs(nums, i) + 1);
        }
        mem.put(index, res);
        return res;
    }
}