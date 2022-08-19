package 剑指Offer二.leetcode_剑指OfferII064_神奇的字典;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
}
class MagicDictionary {

    Set<String> word;
    Map<String, Integer> likeWordCount;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        word = new HashSet<>();
        likeWordCount = new HashMap<>();
    }
    public String[] getNeighbors(String word){
        // 广义邻居的个数=字符串的长度
        String[] neighbors = new String[word.length()];
        StringBuilder str = new StringBuilder(word);
        // 修改字符串中的各位上的字符来生成广义邻居
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            str.setCharAt(i, '*');
            neighbors[i] = str.toString();
            str.setCharAt(i,c);
        }
        return neighbors;
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            word.add(s);
            String[] neighbors = getNeighbors(s);
            for (String neighbor : neighbors) {
                likeWordCount.put(neighbor, likeWordCount.getOrDefault(neighbor, 0)+1);
            }
        }
    }

    public boolean search(String searchWord) {
        String[] neighbors = getNeighbors(searchWord);
        for (String neighbor : neighbors) {
            Integer t;
            if((t = likeWordCount.getOrDefault(neighbor, 0)) > 1 || t == 1 && !word.contains(searchWord))
                return true;
        }
        return false;
    }
}
