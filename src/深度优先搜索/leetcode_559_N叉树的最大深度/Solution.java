package 深度优先搜索.leetcode_559_N叉树的最大深度;


import 深度优先搜索.Node;

public class Solution {
    public int maxDepth(Node root) {
        if(root == null) return 0;
        int max = -1;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max + 1;
    }
}
