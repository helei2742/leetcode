package 动态规划.leetcode_583_两个字符串的删除操作;

import java.sql.ClientInfoStatus;
import java.util.Arrays;

public class Solution {
    public int minDistance(String word1, String word2) {

/*        int len1 = word1.length();
        int len2 = word2.length();

        mem = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            Arrays.fill(mem[i], -1);
        }
        int lsc = dfs(word1,word2,0,0);

        return word1.length() + word2.length() - 2*lsc;*/
        return dp(word1, word2);
    }

    private int[][] mem;
    private int dfs(String word1, String word2, int index1, int index2){
        if(index1 == word1.length())
            return 0;
        if(index2 == word2.length())
            return 0;
        if(mem[index1][index2] != -1) return mem[index1][index2];
        int res = 0;
        if(word1.charAt(index1) == word2.charAt(index2)){
            res =  dfs(word1,word2,index1+1,index2+1)+1;
        }else {
            res = Math.max(dfs(word1, word2, index1, index2+1),
                    dfs(word1, word2, index1+1, index2));
        }
        return mem[index1][index2] = res;
    }

    private int dp(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1+1][len2+1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return len1+len2- 2*dp[len1][len2];
    }
}
