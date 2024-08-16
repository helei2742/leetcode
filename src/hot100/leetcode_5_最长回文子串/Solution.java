package hot100.leetcode_5_最长回文子串;

import java.util.Arrays;

class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
           Arrays.fill(isPalindrome[i], true);
        }
        int maxLen = 0, maxl=0, maxr=0;

        for (int i = len - 1; i >=0; i--) {
            for (int j = i+1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j) && isPalindrome[i+1][j-1]){
                    isPalindrome[i][j] = true;
                    if(j - i + 1 > maxLen){
                        maxLen = j - i + 1;
                        maxl = i;
                        maxr = j;
                    }
                }else {
                    isPalindrome[i][j] = false;
                }
            }
        }

        return s.substring(maxl,maxr+1);
    }
}
