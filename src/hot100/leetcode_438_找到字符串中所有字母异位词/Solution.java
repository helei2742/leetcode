package hot100.leetcode_438_找到字符串中所有字母异位词;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int l1 = s.length(), l2 = p.length();
        int[] need = new int[26], window = new int[26];
        int needCount = 0, curCount = 0;
        for (int i = 0; i < l2; i++) {
            if(need[p.charAt(i) - 'a']++ == 0){
                needCount++;
            }
        }
        int l = 0, r = 0;
        List<Integer> res = new ArrayList<>();

        while(r < l1) {
            int idxR = s.charAt(r) - 'a';
            if(++window[idxR] == need[idxR]) {
                curCount++;
            }
            r++;

            while(r - l >= l2) {
                if(needCount == curCount){
                    res.add(l);
                }
                int idxL = s.charAt(l) - 'a';
                if(window[idxL]-- == need[idxL]){
                    curCount--;
                }
                l++;
            }

        }
        return res;
    }
}
