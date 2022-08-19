package 剑指Offer二.leetcode_剑指OfferII017_含有所有字符的最短字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public String minWindow(String s, String t) {

        int l1 = s.length(), l2 = t.length();
        if(l1<l2){
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        int needCount = 0, curCount = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < l2; i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0)+1);
        }
        needCount = need.size();

        int left = 0, right = 0;
        int resL = 0, resR = Integer.MAX_VALUE;
        while(right<l1){
            char c = s.charAt(right);
            right++;
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(need.getOrDefault(c, -1).equals(map.get(c))){
                curCount++;
            }

            while(left<right-1&&(!need.containsKey(s.charAt(left))
                    || map.getOrDefault(s.charAt(left),0) > need.getOrDefault(s.charAt(left),0))){
                if(!need.containsKey(s.charAt(left))){
                    left++;
                    continue;
                }
                Integer lCount = map.get(s.charAt(left));
                if(lCount == 1){
                    curCount--;
                }
                map.put(s.charAt(left), lCount -1);
                left++;
            }
            if(curCount == needCount&&(right - left < resR-resL)){
                resR = right;
                resL = left;
            }
        }
        if(resR== Integer.MAX_VALUE) return "";
        return s.substring(resL, resR);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abc";
        String t = "cba";
        solution.minWindow(s,t);
    }
}