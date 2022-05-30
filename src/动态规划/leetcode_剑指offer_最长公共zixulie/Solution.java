package 动态规划.leetcode_剑指offer_最长公共zixulie;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        String dfs = dfs(text1, text2, 0, 0);
        System.out.println(dfs);
        return dfs.length();
    }

    public int LCS(String a, String b){
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];
        //转换方向， 1 代表从左上角转换来，2代表从上面转换来，3代表从下面转换来,4哪里都可以
        int[][] dir = new int[n+1][m+1];
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <= m; j++) {
                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    dir[i][j] = 1;
                }else if(dp[i-1][j] > dp[i][j-1]){
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
        Set<String> res = new HashSet<>();

        findLCS(dir, a, "", n,m,res,dp[n][m]);
        System.out.println(res);
        return dp[n][m];
    }
    private void findLCS(int[][] dir, String a, String lcs, int i, int j, Set<String> lcsSet, int maxLen){
        if(i == 0 || j == 0){
            if(lcs.length() == maxLen){
                lcsSet.add(new StringBuilder(lcs).reverse().toString());
            }
            return;
        }

        if(dir[i][j] == 1){
            lcs+=a.charAt(i-1);
            findLCS(dir,a,lcs,i-1,j-1,lcsSet,maxLen);
        }else if(dir[i][j] == 2){
            findLCS(dir,a,lcs,i-1,j,lcsSet,maxLen);
        }else if(dir[i][j] == 3){
            findLCS(dir,a,lcs,i,j-1,lcsSet,maxLen);
        }else{
            findLCS(dir,a,lcs,i,j-1,lcsSet,maxLen);
            findLCS(dir,a,lcs,i-1,j,lcsSet,maxLen);
        }
    }

    private String dfs(String text1, String text2, int index1, int index2){
        if(index1 == text1.length()){
            return "";
        }
        if(index2 == text2.length()){
            return  "";
        }
        StringBuilder res = new StringBuilder();
        if(text1.charAt(index1) == text2.charAt(index2)){
            res.append(text1.charAt(index1));
            res.append(dfs(text1,text2,index1+1,index2+1));
            return res.toString();
        }else {
            String str1 = dfs(text1, text2, index1 + 1, index2);
            String str2 = dfs(text1, text2, index1, index2 + 1);
            if(str1.length() > str2.length()){
                return str1;
            }else{
                return str2;
            }
        }
    }
}
