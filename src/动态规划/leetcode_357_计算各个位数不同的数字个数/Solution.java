package 动态规划.leetcode_357_计算各个位数不同的数字个数;


import java.util.Arrays;

public class Solution {


    public int countNumbersWithUniqueDigits(int n) {
        if(n==0) return 1;
/*        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 9;
        int ans = 10;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] * (11-i);
            ans += dp[i];
        }
        return ans ;*/

        int ans = 10, pre = 9;

        for (int i = 2; i <= n; i++) {
            int t = pre * (11-i);
            ans += t;
            pre = t;
        }
        return  ans;
    }
}
