package hot100.leetcode_543_二叉树的直径;

import 剑指offer.TreeNode;

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {

        dfs(root);
        return res;
    }
    int res = 0;
    private int dfs(TreeNode root) {

        if(root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(left + right, res);
        return Math.max(left, right) + 1;
    }
}
