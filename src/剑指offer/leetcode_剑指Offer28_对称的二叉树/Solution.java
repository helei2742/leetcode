package 剑指offer.leetcode_剑指Offer28_对称的二叉树;

public class Solution {

      public class TreeNode {
          int val;
          TreeNode left;
         TreeNode right;
          TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }
    private boolean check(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}
