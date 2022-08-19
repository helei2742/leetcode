package 剑指Offer二.leetcode_剑指OfferII033_变位词组;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] counter = new int[26];
        Map<String, List<String>> map = new HashMap<>(strs.length);
        StringBuilder sb = null;
        for (String str : strs) {
            Arrays.fill(counter, 0);
            int l = str.length();
            for (int i = 0; i < l; i++) {
                counter[str.charAt(i)-'a']++;
            }
            sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if(counter[i]!=0){
                    sb.append(i+'a').append(counter[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        List<List<String>> res = new ArrayList<>();
        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            res.add(entry.getValue());
        }
        return res;
    }
}