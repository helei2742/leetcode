package 剑指Offer二.leetcode_剑指OfferII051_节点之和最大的路径;

import 剑指offer.TreeNode;

class Solution {
    private int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int t = 0;

        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        t = root.val + left + right;
        res = Math.max(res, t);
        return root.val + Math.max(left, right);
    }
}