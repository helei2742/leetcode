package hot100.leetcode_10_正则表达式匹配;

class Solution {
    public boolean isMatch(String s, String p) {
        int l1 = s.length(), l2 = p.length();
        boolean[][] dp = new boolean[l1+1][l2+1];

        dp[0][0] = true;

        for (int i = 2; i <= l2; i++) {
            if(p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }else {
                    if(p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.'){
                        dp[i][j] = dp[i][j-2];
                    }else {
                        dp[i][j] = dp[i][j-2] | dp[i-1][j-1] | dp[i-1][j];
                    }
                }
            }
        }
        return dp[l1][l2];
    }
}
