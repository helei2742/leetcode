package 动态规划.leetcode_392_判断子序列;

public class Solution {


    public boolean isSubsequence(String s, String t) {
        return twoPointer(s, t);
    }

    private boolean twoPointer(String s, String t) {
        int n = s.length();
        int m = t.length();

        int pointerS = 0, pointerT = 0;

        while (pointerS<=n&&pointerT<=m){
            if(s.charAt(pointerS) == t.charAt(pointerT)){
                pointerS++;
                pointerT++;
            }else {
                pointerT++;
            }
        }

        return pointerS>n;
    }

    private boolean dp(String s, String t){
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[m+1][26];
        for (int i = 0; i < 26; i++) {
            dp[m][i] = m;
        }

        for (int i = m-1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if(t.charAt(i) - 'a' == j){
                    dp[i][j] = i;
                }else {
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        int tStart = 0;
        for (int i = 0; i < n; i++) {
            if(dp[tStart][s.charAt(i)-'a'] == m){
                return false;
            }
            tStart = dp[tStart][s.charAt(i)-'a']+1;
        }
        return true;
    }
}
