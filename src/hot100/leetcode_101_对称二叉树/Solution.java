package hot100.leetcode_101_对称二叉树;

import 剑指offer.TreeNode;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }
    private boolean dfs(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return dfs(left.right,right.left) && dfs(left.left, right.right);
    }
}
