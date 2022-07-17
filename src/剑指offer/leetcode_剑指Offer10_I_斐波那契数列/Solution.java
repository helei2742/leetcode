package 剑指offer.leetcode_剑指Offer10_I_斐波那契数列;

public class Solution {
    public int fib(int n) {
        int[] dp = new int[100];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%1000000007;
        }
        return dp[n-1];
    }
}
