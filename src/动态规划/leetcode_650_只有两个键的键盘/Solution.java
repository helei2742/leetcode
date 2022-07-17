package 动态规划.leetcode_650_只有两个键的键盘;

class Solution {
    public int minSteps(int n) {
//        return dfs(1, 0, n,0, false);
        return dp(n);
    }

    private int dfs(int current, int addValue, int target, int count, boolean lastIsCopy){
        if(current == target) return count;
        if(current > target || current + addValue > target) return Integer.MAX_VALUE;


        int res = Integer.MAX_VALUE;
        if(!lastIsCopy){
            res = Math.min(res,dfs(current, current,target,count+1,true));
        }
        if(addValue!=0){
            res = Math.min(res, dfs(current+addValue, addValue, target,count+1,  false));
        }
        return res;
    }

    private int dp(int n){
        //dp【i】表示组成i个a需要几步
        //i个a，一定是由 j个a，经过复制得到的，次数为 i/j-1次，或者i/j经过 j-1次得到
        //得到dp[i] = Min(dp[j] + i/j - 1,dp[i/j] + j - 1)
        int[] dp = new int[n+1];

        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE/2;
            for (int j = 1; j*j <= i; j++) {
                if(i%j==0){
                    dp[i] = Math.min(dp[i], (dp[i/j] + j - 1) + 1);
                    dp[i] = Math.min(dp[i], (dp[j] + i/j - 1) + 1);
                }
            }
        }
        return dp[n];
    }
}