package hot100.leetcode_208_实现Trie前缀树;

class Trie {
    private Trie[] child;
    boolean isEnd;
    public Trie() {
        child = new Trie[26];
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

    public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(node.child[idx] == null) {
                return false;
            }
            node = node.child[idx];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if(node.child[idx] == null) {
                return false;
            }
            node = node.child[idx];
        }
       return true;
    }
}

