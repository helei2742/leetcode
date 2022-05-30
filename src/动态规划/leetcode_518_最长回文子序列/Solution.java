package 动态规划.leetcode_518_最长回文子序列;

import java.util.Arrays;

class Solution {
    public int longestPalindromeSubseq(String s) {

//        return dfs(s, 0,s.length()-1);
//        int n = s.length();
//        mem = new int[n+1][n+1];
//        for (int i = 0; i <= n; i++) {
//            Arrays.fill(mem[i], -1);
//        }
//        return memdfs(s, 0, n-1);

        return dp(s);
    }

    private int dfs(String s, int start, int end){
        if(start == end) return 1;
        if(start > end) return 0;

        int res = 0;

        if(s.charAt(start) == s.charAt(end)){
            res = dfs(s,start+1,end-1) + 2;
        }else {
            res = Math.max(dfs(s, start, end - 1), dfs(s, start + 1, end));
        }
        return res;
    }

    private int[][] mem;

    private int memdfs(String s, int start, int end) {
        if(start == end) return 1;
        if(start > end) return 0;

        if(mem[start][end] != -1) return mem[start][end];
        int res = 0;
        if(s.charAt(start) == s.charAt(end)){
            res = memdfs(s,start+1,end-1) + 2;
        }else {
            res = Math.max(memdfs(s, start, end - 1),
                    memdfs(s, start + 1, end));
        }
        return mem[start][end] = res;
    }


    private int dp(String s){
        int n = s.length();

        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}