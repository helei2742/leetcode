package 剑指Offer二.leetcode_剑指OfferII054_所有大于等于节点的值之和;

import 剑指offer.TreeNode;

class Solution {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        convertBST(root.right);
        sum+=root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }
}
