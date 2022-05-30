package 动态规划.leetcode_646_最长数对链;

import java.util.Arrays;

public class Solution {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        //dp[i] 代表在pairs数组i位置时所能得到的子哦长数链长度
        int[] dp = new int[n];
        Arrays.sort(pairs, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        Arrays.fill(dp,1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(pairs[j][1] < pairs[i][0]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
