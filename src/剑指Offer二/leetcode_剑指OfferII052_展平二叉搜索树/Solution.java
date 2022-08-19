package 剑指Offer二.leetcode_剑指OfferII052_展平二叉搜索树;

import 剑指offer.TreeNode;

class Solution {
    public TreeNode increasingBST(TreeNode root) {
        res = new TreeNode(0);
        cur = res;
        inorder(root);
        return res.right;
    }
    private TreeNode res;
    private TreeNode cur;
    private void inorder(TreeNode root) {
        if(root == null) return;

        inorder(root.left);
        cur.right = root;
        root.left = null;
        cur = root;
        inorder(root.right);
    }
}