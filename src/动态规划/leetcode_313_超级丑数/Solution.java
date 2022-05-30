package 动态规划.leetcode_313_超级丑数;


import java.util.Arrays;

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        //pointers[i]表示下一个超级丑数是当前指针指向的超级丑数乘以对应的质因数
        // （primes[i]位置的质因数
        //因此下一个超级丑数是 dp[pointers[i]] * primes[i]
        int[] pointers = new int[primes.length];
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                dp[i] = Math.min(dp[i], dp[pointers[j]] * primes[j]);
            }
            for (int j = 0; j < m; j++) {
                if (dp[i] == dp[pointers[j]] * primes[j]) {
                    pointers[j]++;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n-1];
    }
}
