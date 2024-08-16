package hot100.leetcode_236_二叉树的最近公共祖先;

import 剑指offer.TreeNode;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        isHave(root, p, q);
        return res;
    }
    private TreeNode res;

    private boolean isHave(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return false;

        boolean left = isHave(root.left, p, q);
        boolean right = isHave(root.right, p, q);

        if((left&&right) || ((left||right) && (root==p||root==q))){
            res = root;
            return true;
        }
        return left || right || root == p || root == q;
    }
}
