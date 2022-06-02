package DFS.leetcode_124_二叉树的最大路径和;

import DFS.TreeNode;

public class Solution {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    private int dfs(TreeNode root){
        if(root == null) return 0;

        int lmax = Math.max(dfs(root.left), 0);
        int rmax = Math.max(dfs(root.right), 0);

        int curValue = root.val + lmax + rmax;
        max = Math.max(max, curValue);
        return root.val + Math.max(lmax, rmax);
    }
}
