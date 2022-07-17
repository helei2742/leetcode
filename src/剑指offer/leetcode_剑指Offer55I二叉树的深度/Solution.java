package 剑指offer.leetcode_剑指Offer55I二叉树的深度;

import 剑指offer.TreeNode;

public class Solution {
    public int maxDepth(TreeNode root) {
        return height(root);
    }

    private int height(TreeNode root){
        if(root == null) return 0;
        int res = 0;
        res = Math.max(height(root.left), height(root.right))+1;
        return res;
    }
}
