package hot100.leetcode_72_编辑距离;

import java.util.Arrays;

class Solution {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
/*
        int[][] dp = new int[l1+1][l2+1];

        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= l2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                //替换 dp[i-1][j-1] ,删除dp[i-1][j]或dp[i][j-1]
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }
            }
        }
        return dp[l1][l2];
        */
        mem = new int[l1+1][l2+1];
        for (int i = 0; i <= l1; i++) {
            Arrays.fill(mem[i], -1);
        }
        return dfs(word1, l1, word2, l2);
    }
    private int[][] mem;
    private int dfs(String word1, int idx1, String word2, int idx2) {
        if (mem[idx1][idx2] != -1) return mem[idx1][idx2];
        if(idx1 == 0){
            return idx2;
        }
        if(idx2 == 0){
            return idx1;
        }
        if(word1.charAt(idx1-1) == word2.charAt(idx2-1)) {
            return mem[idx1][idx2] = dfs(word1, idx1-1, word2, idx2-1);
        }
        return mem[idx1][idx2] = Math.min(
                dfs(word1,idx1-1,word2,idx2-1), Math.min(
                        dfs(word1,idx1-1,word2,idx2),
                        dfs(word1,idx1,word2,idx2-1)))+1;
    }
}
