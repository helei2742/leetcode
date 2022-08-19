package 剑指Offer二.leetcode_剑指OfferII063_替换单词;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();

        for (String s : dictionary) {
            root.insert(s);
        }
        String[] splits = sentence.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String search = root.search(splits[i]);
            sb.append(search);
            if(i != splits.length - 1){
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    class Trie{
        public Trie[] child;
        public boolean isEnd;
        public Trie(){
            this.child = new Trie[26];
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(node.child[idx] == null) {
                    node.child[idx] = new Trie();
                }
                node = node.child[idx];
            }
            node.isEnd = true;
        }

        public String search(String word) {
            Trie node = this;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int idx = c - 'a';
                if(node.child[idx] == null || node.isEnd) {
                    break;
                }
                sb.append(c);
                node = node.child[idx];
            }

            String str = sb.toString();

            if(!node.isEnd || str.isEmpty()){
                return word;
            }
            return str;
        }
    }
}
