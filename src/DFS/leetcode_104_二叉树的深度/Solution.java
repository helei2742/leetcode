package DFS.leetcode_104_二叉树的深度;

import DFS.TreeNode;

public class Solution {
    private int max = 0;
    public int maxDepth(TreeNode root) {
        dfs(root, 0);
        return max;
    }
    private void dfs(TreeNode root, int dep) {
        if(root == null) return;
        max = Math.max(max, dep);
        dfs(root.right, dep+1);
        dfs(root.left, dep+1);
    }
}
