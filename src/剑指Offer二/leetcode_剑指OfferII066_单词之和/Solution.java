package 剑指Offer二.leetcode_剑指OfferII066_单词之和;

public class Solution {
}

class MapSum {

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Tire();
    }
    private Tire root;
    public void insert(String key, int val) {
        root.insert(key, val);
    }

    public int sum(String prefix) {
        return root.queryPreVal(prefix);
    }
    class Tire{
        public Tire[] child;
        public int val;
        public Tire(){
            this.child = new Tire[26];
        }

        public void insert(String word, int val) {
            Tire node = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(node.child[idx] == null) {
                    node.child[idx] = new Tire();
                }
                node=node.child[idx];
            }
            node.val = val;
        }
        int dfs(Tire node) {
            if (node == null) return 0;
            int childSum = 0;
            for (int i = 0; i < 26; ++i) {
                if (node.child[i] != null) {
                    childSum += dfs(node.child[i]);
                }
            }
            return node.val + childSum;
        }


        public int queryPreVal(String pre) {
            Tire node = this;
            for (int i = 0; i < pre.length(); i++) {
                int idx = pre.charAt(i) - 'a';
                if(node.child[idx] == null) {
                    return 0;
                }
                node=node.child[idx];
            }
            return dfs(node);
        }
    }
}
