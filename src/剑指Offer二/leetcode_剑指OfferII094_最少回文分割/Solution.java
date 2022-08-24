package 剑指Offer二.leetcode_剑指OfferII094_最少回文分割;

import java.util.Arrays;

class Solution {
    public int minCut(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();

        boolean[][] isValid = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            Arrays.fill(isValid[i], true);
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                isValid[i][j] = chars[i] == chars[j] && isValid[i+1][j-1];
            }
        }

        int[] dp = new int[len+1];

        for (int i = 1; i <= len; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if(isValid[j][i-1]){
                    dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }
        return dp[len-1]-1;
    }
}
