package 剑指offer.leetcode_剑指Offer46_把数字翻译成字符串;

import java.util.Arrays;

class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int [] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            dp[i] = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? dp[i-1] + dp[i-2] : dp[i-1];
        }
        return dp[s.length()];
    }

}
