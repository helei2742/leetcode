package 动态规划.leetcode_124_二叉树中的最大路径和;


import 动态规划.TreeNode;

public class Solution {
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }
    private int ans = Integer.MIN_VALUE;
    private int dfs(TreeNode root){
        if(root == null) return 0;
        int res = root.val;
        int left = Math.max(dfs(root.left),0);
        int right = Math.max(dfs(root.right),0);

        ans = Math.max(ans, res + left + right);
        return res + Math.max(left, right);
    }
}
