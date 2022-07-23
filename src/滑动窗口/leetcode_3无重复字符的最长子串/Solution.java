package 滑动窗口.leetcode_3无重复字符的最长子串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int len = s.length();
        int r = -1, ans = 0;
        for (int i = 0; i < len; i++) {
            if(i!=0){
                set.remove(s.charAt(i-1));
            }

            while (r+1<len && !set.contains(s.charAt(r+1))){
                set.add(s.charAt(r+1));
                r++;
            }
            ans = Math.max(ans, r-i+1);
        }
        return ans;
    }
    
    public int sw(String s){
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, ans = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                left = Math.max(left, map.get(c)+1);
            }
            map.put(s.charAt(i), i);
            ans = Math.max(ans, i-left+1);
        }
        return ans;
    }
}