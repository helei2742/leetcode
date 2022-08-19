package 滑动窗口.leetcode_438_找到字符串中所有字母异位词;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26], window = new int[26];
        int m = p.length();
        int totalChar = 0;
        for (int i = 0; i < m; i++) {
            if(need[p.charAt(i)-'a']==0){
                totalChar++;
            }
            need[p.charAt(i)-'a']++;
        }
        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        while (right<s.length()){
            int idx = s.charAt(right)-'a';
            right++;
            if(need[idx] > 0) {
                window[idx]++;
                if (window[idx] == need[idx]) valid++;
            }

            while (right-left>=p.length()){
                if(valid == totalChar){
                    res.add(left);
                }
                int lIdx = s.charAt(left)-'a';
                left++;
                if(need[lIdx] > 0) {
                    if (window[lIdx] == need[lIdx]) {
                        valid--;
                    }
                    window[lIdx]--;
                }
            }
        }
        return res;
    }
}
