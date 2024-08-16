package hot100.leetcode_32_最长有效括号;

class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        int[] dp = new int[len];
        int res = 0;
        for (int i = 1; i < len; i++) {
            if(s.charAt(i) == ')'){
                if(s.charAt(i-1) == '('){
                    dp[i] = (i >= 2 ? dp[i-2]: 0) + 2;
                }else if(i-dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '('){
                    dp[i] = dp[i-1] + 2 +(i-dp[i-1]>=2? dp[i-dp[i-1]-2]:0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
