package 剑指Offer二.leetcode_剑指OfferII097_子序列的数目;


class Solution {
    public int numDistinct(String s, String t) {
        int l1 = s.length(), l2 = t.length();
        int[][] dp = new int[l1+1][l2+1];

        for (int i = 0; i < l1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[l1][l2];
    }
}
