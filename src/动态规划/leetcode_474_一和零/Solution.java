package 动态规划.leetcode_474_一和零;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
/*        int len = strs.length;
        count = new int[len][2];

        for (int i = 0; i < len; i++) {
            count[i] = getCount(strs[i]);
        }

//        return dfs(m,n,0,len);

        mem = new HashMap<>();
        return memdfs(m,n,0,len);*/

        return dp(strs,m,n);
    }


    private int dfs(int m, int n, int index, int len) {
        if(index >= len) return 0;
        int res = 0;
        int zero = count[index][0];
        int one = count[index][1];
        if(m-zero>=0&&n-one>=0){
            res = Math.max(dfs(m-zero, n-one, index+1, len)+1, res);
        }
        res = Math.max(dfs(m,n,index+1,len),res);
        return res;
    }

    private Map<String, Integer> mem;
    private int memdfs(int m, int n, int index, int len){
        if(index>=len) return 0;
        String key = m+"-"+n+"-"+len;

        if(mem.containsKey(key)) return mem.get(key);

        int res = 0;
        int zero = count[index][0];
        int one = count[index][1];
        if(m-zero>=0&&n-one>=0){
            res = Math.max(memdfs(m-zero, n-one, index+1, len)+1, res);
        }
        res = Math.max(memdfs(m,n,index+1,len),res);

        mem.put(key, res);
        return res;
    }

    private int dp(String[] strs, int m, int n){
        int len = strs.length;

        int[][][] dp = new int[len+1][m+1][n+1];

        for (int i = 1; i <= len; i++) {
            int[] count = getCount(strs[i - 1]);
            int zero = count[0];
            int one = count[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i-1][j][k];
                    if(j-zero>=0 && k-one>=0){
                        dp[i][j][k] = Math.max(dp[i][j][k],dp[i-1][j-zero][k-one]+1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    private int[][] count;
    private int[] getCount(String str) {
        int zero = 0, one = 0;

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '0'){
                zero++;
            }else {
                one++;
            }
        }
        return new int[]{zero,one};
    }
}
