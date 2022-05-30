package 动态规划.leetcode_115_不同的子序列;

import java.util.Arrays;

public class Solution {
    public int numDistinct(String s, String t) {
/*        int lS = s.length();
        int lT = t.length();
        if(lS < lT) return 0;

//        return find(s,t,lS-1,lT-1);

        mem = new int[lS][lT];
        for (int i = 0; i < lS; i++) {
            Arrays.fill(mem[i], -1);
        }

        return memfind(s,t,lS-1,lT-1);*/

        return dp(s,t);
    }

    private int find(String s, String t, int indexS, int indexT){
        //空串是任何串的子序列，返回1
        if(indexT<0) return 1;
        //空串不能生成除了空串以外的序列
        if(indexS<0) return 0;

        int res = 0;
        //相等，则分取s串中该位置的情况和不取该位置的情况
        if(s.charAt(indexS) == t.charAt(indexT)){
            res = find(s,t,indexS-1,indexT-1) + find(s,t,indexS-1,indexT);
        }else {
            res = find(s,t,indexS-1,indexT);
        }

        return res;
    }

    private int[][] mem;
    private int memfind(String s, String t, int indexS, int indexT){
        if(mem[indexS][indexT]!=-1){
            return mem[indexS][indexT];
        }
        if(indexT < 0) return 1;
        if(indexS < 0) return 0;
        int res = 0;
        if(s.charAt(indexS) == t.charAt(indexT)){
            res = memfind(s, t, indexS-1, indexT-1) + memfind(s, t, indexS-1, indexT);
        }else {
            res = memfind(s, t, indexS-1, indexT);
        }
        return mem[indexS][indexT] = res;
    }


    private int dp(String s, String t){
        int lS = s.length();
        int lT = t.length();
        if(lS < lT) return 0;

        int[][]dp= new int[lS+1][lT+1];

        for (int i = 0; i <= lT; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= lS; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= lS; i++) {
            for (int j = 1; j <= lT; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[lS][lT];
    }
}
