package 剑指Offer二.leetcode_剑指OfferII095_最长公共子序列;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length(), l2 = text2.length();
        int[][] dp = new int[l1+1][l2+1];
        int[][] dir = new int[l1+1][l2+1];

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    dir[i][j] = 1;
                }else if(dp[i-1][j] > dp[i][j-1]) {
                    dp[i][j] = dp[i-1][j];
                    dir[i][j] = 2;
                }else if(dp[i-1][j] < dp[i][j-1]){
                    dp[i][j] = dp[i][j-1];
                    dir[i][j] = 3;
                }else {
                    dp[i][j] = dp[i][j-1];
                    dir[i][j] = 4;
                }
            }
        }

        Set<String> set = new HashSet<>();
        dfs(dir, text1, "", l1, l2, set, dp[l1][l2]);
        System.out.println(set);
        return dp[l1][l2];
    }
    private void dfs(int[][]dir, String s, String lcs, int i, int j, Set<String> lcsString, int tar){
        if(i==0||j==0){
            if(lcs.length() == tar){
                lcsString.add(new StringBuilder(lcs).reverse().toString());
            }
            return;
        }
        if(dir[i][j] == 1) {
            dfs(dir, s, lcs + s.charAt(i), i-1, j-1, lcsString, tar);
        }else if(dir[i][j] == 2) {
            dfs(dir, s, lcs, i-1, j, lcsString, tar);
        }else if(dir[i][j] == 3){
            dfs(dir, s, lcs, i, j-1, lcsString, tar);
        }else {
            dfs(dir, s, lcs, i-1, j, lcsString, tar);
            dfs(dir, s, lcs, i, j-1, lcsString, tar);
        }
    }


    public int longestCommonSubsequence2(String text1, String text2) {
        int l1 = text1.length(), l2 = text2.length();

        mem = new int[l1][l2];
        for (int i = 0; i < l1; i++) {
            Arrays.fill(mem[i], -1);
        }
        return fn(text1,text2,0,0);
    }

    int[][] mem;
    private int fn(String text1, String text2, int idx1, int idx2) {
        if(idx1 == text1.length()) {
            return 0;
        }
        if(idx2 == text2.length()) {
            return 0;
        }
        if(mem[idx1][idx2] != -1) return mem[idx1][idx2];
        int res = 0;
        if(text1.charAt(idx1) == text2.charAt(idx2)) {
            res = fn(text1, text2, idx1+1,idx2+1) + 1;
        }else {
            int fn1 = fn(text1, text2, idx1 + 1, idx2);
            int fn2 = fn(text1, text2, idx1, idx2 + 1);
            res = Math.max(fn1, fn2);
        }
        return mem[idx1][idx2] = res;
    }
}
