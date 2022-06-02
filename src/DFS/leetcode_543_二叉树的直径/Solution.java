package DFS.leetcode_543_二叉树的直径;

import DFS.TreeNode;

public class Solution {

    public int diameterOfBinaryTree(TreeNode root) {
        return dfs(root);
    }
    private int ans = Integer.MIN_VALUE;
    private int dfs(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null)
            return 1;

        int lHeight = dfs(root.left);
        int rHeight = dfs(root.right);
        ans = Math.max(ans, lHeight + rHeight);
        return Math.max(lHeight, rHeight) + 1;
    }
}
