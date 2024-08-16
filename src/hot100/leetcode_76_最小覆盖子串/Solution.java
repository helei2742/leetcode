package hot100.leetcode_76_最小覆盖子串;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        int l1 = s.length(), l2 = t.length();
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        int needCount = 0, curCount = 0;
        for (int i = 0; i < l2; i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        needCount = need.size();

        int left = 0, right = 0;

        int resL = 0, resR = Integer.MAX_VALUE;
        while(right < l1){
            char rChar = s.charAt(right);
            window.put(rChar, window.getOrDefault(rChar, 0) + 1);
            if(window.get(rChar).equals(need.getOrDefault(rChar, 0))){
                curCount++;
            }
            right++;

            char lChar;
            while(left < right - 1 &&
                    (!need.containsKey(lChar = s.charAt(left)) || need.get(lChar) < window.get(lChar))){
                if(!need.containsKey(lChar)){
                    left++;
                    continue;
                }

                window.put(lChar, window.get(lChar) - 1);
                if(window.get(lChar) == 0) {
                    curCount--;
                }
                left++;
            }
            if(curCount == needCount && right-left < resR-resL){
                resR = right;
                resL = left;
            }
        }
        if(resR== Integer.MAX_VALUE) return "";
        return s.substring(resL, resR);
    }
}
