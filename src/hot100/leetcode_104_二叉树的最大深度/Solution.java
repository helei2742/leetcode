package hot100.leetcode_104_二叉树的最大深度;

import 剑指offer.TreeNode;

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int res = 0;
        res = Math.max(res, maxDepth(root.left));
        res = Math.max(res, maxDepth(root.right));
        return res+1;
    }
}
