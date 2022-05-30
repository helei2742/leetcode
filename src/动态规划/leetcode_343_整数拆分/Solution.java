package 动态规划.leetcode_343_整数拆分;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int integerBreak(int n) {
//        mem = new int[n+1];
//        Arrays.fill(mem, -1);
//
//        return dfs(n);

        return dp(n);
    }
    private int [] mem;
    private int dfs(int num){
        if(num == 1) return 1;
        if(mem[num] != -1) return mem[num];

        int res = 0;

        for (int i = 1; i < num; i++) {
            int e = Math.max(i*dfs(num-i), i*(num-i));
            res = Math.max(res, e);
        }
        return mem[num] = res;
    }

    private int dp(int num) {

        int[] dp = new int[num+1];

        for (int i = 2; i <= num; i++) {
            int temp = 0;

            for (int j = 1; j < i; j++) {
                temp = Math.max(Math.max(j *(i-j), j*dp[i-j]),temp);
            }
            dp[i] = temp;
        }
        return dp[num];
    }
}
