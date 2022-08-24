package 剑指Offer二.leetcode_剑指OfferII096_字符串交织;

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length();
        if(l1 + l2 != s3.length()) return false;

        boolean[][] dp = new boolean[l1+1][l2+1];
        dp[0][0] = true;
        for (int i = 0; i < l1; i++) {
            if(s1.charAt(i) == s3.charAt(i)){
                dp[i+1][0] = true;
            }else break;
        }
        for (int i = 0; i < l2; i++) {
            if(s2.charAt(i) == s3.charAt(i)){
                dp[0][i+1] = true;
            }else break;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                int idx = i + j;
                if(s1.charAt(i - 1) == s3.charAt(idx-1)){
                    dp[i][j] |= dp[i-1][j];
                }
                if(s2.charAt(j-1) == s3.charAt(idx-1)){
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }
        return dp[l1][l2];
    }
}
