package 剑指offer.leetcode_剑指Offer10_II_青蛙跳台阶问题;

class Solution {
    public int numWays(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            dp[i]%=1e9+7;
        }
        return dp[n];
    }
    public int sleepWindow(int n) {
        if(n<1) return 0;
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 2; i <= n; i++) {
            c = (a+b);
            c %= 1e9+7;
            a = b;
            b = c;
        }
        return c;
    }
}