package 滑动窗口.leetcode_187_重复的DNA序列;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();

        List<String> res = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();

        for (int i = 0; i < len-10; i++) {
            String substring = s.substring(i, i + 10);
            int count = map.getOrDefault(substring, 0);
            if(count == 1){
                res.add(substring);
            }
            map.put(substring, count+1);
        }
        return res;
    }
}
