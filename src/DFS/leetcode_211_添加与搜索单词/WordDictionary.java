package DFS.leetcode_211_添加与搜索单词;

import java.util.HashSet;
import java.util.Set;

public class WordDictionary {

    private Tree root = null;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Tree();
    }

    public void addWord(String word) {

        Tree head = root;

        for (int i = 0; i < word.length(); i++) {
            int cur = word.charAt(i) - 'a';
            if(head.childes[cur] == null) {
                head.childes[cur] = new Tree();
            }
            head = head.childes[cur];
        }
        head.isOver = true;
    }

    public boolean search(String word) {
        Tree head = root;
        return dfs(head, 0, word);
    }

    private boolean dfs(Tree currentNode, int index, String word) {
        if(index == word.length()) {
            return currentNode.isOver;
        }

        char aChar = word.charAt(index);

        if(aChar == '.') {
            for (int i = 0; i < 26; i++) {
                if(currentNode.childes[i] != null) {
                    if(dfs(currentNode.childes[i], index + 1, word)) return true;
                }
            }
        } else {
            int cur = aChar - 'a';
            if(currentNode.childes[cur] != null) {
                if(dfs(currentNode.childes[cur], index + 1, word)) return true;
            }
        }

        return false;
    }

    class Tree {
        Tree[] childes;
        boolean isOver;
        Tree(){
            childes = new Tree[26];
            isOver = false;
        }
    }

}
