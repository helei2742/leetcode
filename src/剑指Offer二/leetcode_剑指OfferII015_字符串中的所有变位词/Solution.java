package 剑指Offer二.leetcode_剑指OfferII015_字符串中的所有变位词;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26], window = new int[26];
        int needCount = 0;
        int l1 = s.length(), l2 = p.length();
        for (int i = 0; i < l2; i++) {
            if(need[p.charAt(i)-'a']++ == 0) needCount++;
        }

        int left = 0, right = 0, curCount = 0;
        List<Integer> res = new ArrayList<>(s.length());

        while(right < l1){
            int rIdx = s.charAt(right) - 'a';
            right++;
            window[rIdx]++;
            if(window[rIdx] == need[rIdx]) curCount++;

            while(right - left >= l2){
                if(curCount == needCount){
                    res.add(left);
                }

                int lIdx = s.charAt(left) - 'a';
                left++;
                if(window[lIdx] == need[lIdx]) curCount--;
                window[lIdx]--;
            }
        }
        return res;
    }
}