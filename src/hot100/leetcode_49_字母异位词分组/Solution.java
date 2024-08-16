package hot100.leetcode_49_字母异位词分组;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] bucket = new int[26];
            for (int i = 0; i < str.length(); i++) {
                bucket[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if(bucket[i] != 0)
                    sb.append((char)(i+'a')).append(bucket[i]);
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Collection<List<String>> values = Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
            int[] b = new int[26];
            for (int i = 0; i < str.length(); i++) {
                b[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : b) {
                sb.append(i).append("-");
            }
            return sb.toString();
        })).values();
        return new ArrayList<>(values);

    }
}
