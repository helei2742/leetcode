package 剑指offer.leetcode_剑指Offer55_II平衡二叉树;

import 剑指offer.TreeNode;

public class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) > 0;
    }

    private int height(TreeNode root){
        if(root == null) return 0;
        int lH = height(root.left);
        int rH = height(root.right);
        if(lH==-1||rH==-1||Math.abs(lH - rH)>1){
            return -1;
        }else {
            return Math.max(lH,rH)+1;
        }
    }
}
