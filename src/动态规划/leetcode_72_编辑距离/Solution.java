package 动态规划.leetcode_72_编辑距离;

import java.util.Arrays;

public class Solution {

    private int[][] mem;
    public int minDistance(String word1, String word2) {
/*        mem = new int[word1.length()+1][word2.length()+1];
        for (int[] ints : mem) {
            Arrays.fill(ints, -1);
        }

        return dfs(word1,word2,0,0);*/
        return dp(word1,word2);
    }

    private int dp(String word1, String word2){
        int len1 = word1.length(), len2 = word2.length();
        //dp[i][j]  表示i长度的word1 到 j长度的word2的最短编辑距离
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    private int dfs(String word1, String word2, int index1, int index2){
        if(mem[index1][index2]!=-1) return mem[index1][index2];

        if(index1 == word1.length()){
            return mem[index1][index2] = word2.length() - index2;
        }
        if(index2 == word2.length()){
            return mem[index1][index2] = word1.length() - index1;
        }
        if(word1.charAt(index1) == word2.charAt(index2)){
            return dfs(word1, word2, index1 + 1, index2 + 1);
        }
        //依次为替换（word1、a插入）dfs(word1,word2,index1 + 1,index2 + 1)
        // 删除a dfs(word1,word2,index1+1,index2)
        // 删除b dfs(word1,word2,index1,index2+1)
        return mem[index1][index2] = Math.min(dfs(word1,word2,index1 + 1,index2 + 1),
                Math.min(dfs(word1,word2,index1+1,index2),
                        dfs(word1,word2,index1,index2+1))) + 1;
    }
}
