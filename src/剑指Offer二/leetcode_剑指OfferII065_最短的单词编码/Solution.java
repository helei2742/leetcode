package 剑指Offer二.leetcode_剑指OfferII065_最短的单词编码;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Tire root = new Tire();
        for (String word : words) {
            root.insert(word);
        }
        int res = 0;
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if(set.contains(word)) continue;
            set.add(word);
            if(!root.isNotLeaf(word)){
                res += word.length() + 1;
            }
        }
        return res;
    }
    class Tire{
        public Tire[] child;
        public boolean isEnd;
        public Tire(){
            this.child = new Tire[26];
        }

        public void insert(String word) {
            Tire node = this;
            for (int i = word.length() - 1; i >= 0; i--) {
                int idx = word.charAt(i) - 'a';
                if(node.child[idx] == null) {

                    node.child[idx] = new Tire();
                }
                node = node.child[idx];
            }
            node.isEnd = true;
        }
        public boolean isNotLeaf(String word) {
            Tire node = this;
            int length = word.length();
            for (int i = length - 1; i >= 0; i--) {
                int idx = word.charAt(i) - 'a';
                if(node.child[idx] == null) {
                    return false;
                }
                node = node.child[idx];
            }

            for (int i = 0; i < 26; i++) {
                if(node.child[i] != null) return true;
            }
            return false;
        }
    }
}
