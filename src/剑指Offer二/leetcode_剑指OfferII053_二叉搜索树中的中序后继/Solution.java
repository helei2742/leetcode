package 剑指Offer二.leetcode_剑指OfferII053_二叉搜索树中的中序后继;

import 剑指offer.TreeNode;

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return res;
    }
    private TreeNode res;
    private boolean f;

    private void inorder(TreeNode root, TreeNode p){
        if(root == null) return;

        inorder(root.left, p);
        if(root == p){
            f = true;
        }else if(f){
            res = root;
            f = false;
        }
        inorder(root.right, p);
    }

    private TreeNode fn(TreeNode root, TreeNode p) {
        TreeNode res = null;
        if(p.right != null){
            res = p.right;
            while(res.left != null) {
                res = res.left;
            }
            return res;
        }
        TreeNode cur = root;
        while(cur != null) {
            if(cur.val > p.val){
                res = cur;
                cur = cur.left;
            }else if(cur.val < p.val){
                cur = cur.right;
            }else {
                break;
            }
        }
        return res;
    }
}
