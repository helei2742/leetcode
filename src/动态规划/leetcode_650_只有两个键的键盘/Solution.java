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
        int[] dp = new int[n+1];

        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE/2;
            for (int j = 1; j*j <= i; j++) {
                if(i%j==0){
                    dp[i] = Math.min(dp[i], dp[i/j] + j -1 + 1);
                    dp[i] = Math.min(dp[i], dp[j] + i/j - 1 + 1);
                }
            }
        }
        return dp[n];
    }
}