package 滑动窗口.leetcode_395_至少有K个重复字符的最长子串;

import java.util.Arrays;

public class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();

        int ans = 0;
        int[] cnt = new int[26];
        for (int i = 1; i <= 26; i++) {
            Arrays.fill(cnt, -1);
            for (int l = 0, r = 0, tot = 0, sum = 0; r < n;r++) {
                int c = s.charAt(r)-'a';
                cnt[c]++;
                if(cnt[c] == 1) tot++;
                if(cnt[c] == k) sum++;

                while (tot>i){
                    int c1 = s.charAt(l++)-'a';
                    cnt[c1]--;
                    if(cnt[c1]==0) tot--;
                    if(cnt[c1]==k-1) sum--;
                }
                if(sum==tot) ans = Math.max(ans, r-l+1);
            }
        }
        return ans;
    }
}
