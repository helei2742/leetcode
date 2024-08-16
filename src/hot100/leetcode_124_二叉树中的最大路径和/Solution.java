package hot100.leetcode_124_二叉树中的最大路径和;

import 剑指offer.TreeNode;

class Solution {
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }
    private int res = Integer.MIN_VALUE;
    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(0, dfs(root.right));

        if(res < left + right + root.val){
            res = left+right+root.val;
        }
        return root.val + Math.max(left, right);
    }
}
