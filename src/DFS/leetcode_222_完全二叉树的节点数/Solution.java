package DFS.leetcode_222_完全二叉树的节点数;

import DFS.TreeNode;

public class Solution {
    private int count = 0;
    public int countNodes(TreeNode root) {
        dfs(root);
        return count;
    }

    public void dfs(TreeNode root) {
        if(root != null) {
            count++;
            dfs(root.left);
            dfs(root.right);
        }
    }
}
