package hot100.leetcode_114_二叉树展开为链表;

import 剑指offer.TreeNode;

class Solution {
    public void flatten(TreeNode root) {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if(root == null) return;

        inorder(root.left);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while(root.right != null){
            root = root.right;
        }
        inorder(right);
        root.right = right;
    }
}
