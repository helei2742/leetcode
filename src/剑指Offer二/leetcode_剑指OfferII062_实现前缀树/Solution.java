package 剑指Offer二.leetcode_剑指OfferII062_实现前缀树;

public class Solution {
}

class Trie {
    public Trie[] child;
    public boolean isEnd;
    /** Initialize your data structure here. */
    public Trie() {
        child = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(node.child[idx] == null){
                node.child[idx] = new Trie();
            }
            node = node.child[idx];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie trie = searchPrefix(prefix);
        return trie != null;
    }
    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if(node.child[idx] == null){
                return null;
            }
            node = node.child[idx];
        }
        return node;
    }
}
