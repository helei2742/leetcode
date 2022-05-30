package 动态规划.leecode_140_单词拆分2;

import java.util.*;

public class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        len = s.length();
        map = new HashMap<>();
        List<List<String>> dfs = dfs(s, 0, new HashSet<>(wordDict));
        List<String> ans = new LinkedList<>();
        for (List<String> df : dfs) {
            ans.add(String.join(" ", df));
        }
        return ans;
    }
    private Map<Integer, List<List<String>>> map;
    private int len;
    private List<List<String>> dfs(String s, int index, Set<String> words){

        if(map.containsKey(index)) return map.get(index);

        List<List<String>> res = new LinkedList<>();

        if(index == len){
            res.add(new LinkedList<>());
        }
        for (int i = index+1; i <= len; i++) {
            String word = s.substring(index, i);
            if(words.contains(word)){
                List<List<String>> nextRes = dfs(s, i, words);
                for (List<String> stringList : nextRes) {
                    LinkedList<String> temp = new LinkedList<>(stringList);
                    temp.offerFirst(word);
                    res.add(temp);
                }
            }
        }
        map.put(index, res);
        return res;
    }
}
