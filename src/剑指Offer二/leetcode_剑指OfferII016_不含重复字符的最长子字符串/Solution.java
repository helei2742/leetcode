package 剑指Offer二.leetcode_剑指OfferII016_不含重复字符的最长子字符串;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIdx = new HashMap<>();
        int len = s.length();
        int res = 0;
        int left = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(lastIdx.containsKey(c)){
                left = Math.max(left, lastIdx.get(c) + 1);
            }
            lastIdx.put(c, i);
            res = Math.max(res, i-left+1);
        }
        return res;
    }
}
