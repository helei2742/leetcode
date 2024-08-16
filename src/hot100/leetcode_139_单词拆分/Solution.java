package hot100.leetcode_139_单词拆分;

import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        map = new HashMap<>();
        return dfs(s, 0);
    }
    private Set<String> set;
    private Map<Integer,Boolean> map = new HashMap<>();
    private boolean dfs(String s, int idx) {
        if(map.containsKey(idx)) return map.get(idx);
        if(idx == s.length()) return true;
        boolean f = false;
        for (int i = idx+1; i <= s.length(); i++) {
            String substring = s.substring(idx, i);
            if(set.contains(substring)) {
                f |= dfs(s, i);
            }
        }
        map.put(idx, f);
        return f;
    }
}
