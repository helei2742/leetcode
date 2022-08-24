package 剑指Offer二.leetcode_剑指OfferII092_翻转字符;

import java.util.Arrays;

class Solution {
    public int minFlipsMonoIncr(String s) {
/*        int len = s.length();

        int[][] dp = new int[len][2];
        char[] chars = s.toCharArray();
        dp[0][0] = chars[0] == '0'?0:1;
        dp[0][1] = chars[0] == '1'?0:1;

        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i-1][0] + (chars[i] == '0'?0:1);
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + (chars[i] == '1'?0:1);
        }
        return Math.min(dp[len-1][0], dp[len-1][1]);*/



       return dfs(s.toCharArray(), 0, false);
    }

    private int[][] mem;
    private int dfs(char[] chars, int idx, boolean preIsOne) {
        if(idx == chars.length) return 0;

        if(preIsOne) {
            int res = 0;
            for (int i = idx; i < chars.length; i++) {
                res += chars[i] == '0'?1:0;
            }
            return res;
        }

        int res = 0;
        if(chars[idx] == '0') {
            int dfs1 = dfs(chars, idx + 1, false);
            int dfs2 = dfs(chars, idx + 1, true) + 1;
            res =  Math.min(dfs1, dfs2);
        }else {
            int dfs1 = dfs(chars, idx + 1, false)  + 1;
            int dfs2 = dfs(chars, idx + 1, true);
            res = Math.min(dfs1, dfs2);
        }
        return res;
    }
}
