package 记忆化搜索.leetcode_139_单词拆分;

import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs2(s, 0, set);
    }

    //记录不能用字典中的单词拼接成的字符串
    private Set<String> mem = new HashSet<>();

    //dfs返回，s字符串是否能被set中的单词拼成
    private boolean dfs(String s, Set<String> set){
        if(s == null || s.length() == 0) return true;
        //s字符串不能由字典拼接，直接返回false
        if(mem.contains(s)) return false;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.indexOf(i));
            String otherStr = s.substring(i+1);
            //当前切割的串sb能用字典拼成，并且剩下的不会不能被拼成
            if(set.contains(sb.toString())&&!mem.contains(otherStr)){
                //剩下的也能拼成，返回ture
                if(dfs(otherStr, set)){
                    return true;
                }else {
                    //剩下的不能拼成,记录该串
                    mem.add(otherStr);
                }
            }
        }

        mem.add(s);
        return false;
    }

    private Map<Integer, Boolean> map = new HashMap<>();
    private boolean dfs2(String s, int index, Set<String> set){
        if(map.containsKey(index)) return map.get(index);
        if(index == s.length()) return true;

        boolean res = false;
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if(set.contains(word)){
                res |= dfs2(s, i, set);
            }
        }
        map.put(index, res);
        return res;
    }

}
