package 深度优先搜索.leetcode_687_最长同值路径;


import 深度优先搜索.TreeNode;



class Solution {
    private int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }
    private int dfs(TreeNode root){
        if(root == null) return 0;
        int leftLen = dfs(root.left);
        int rightLen = dfs(root.right);
        int left = 0;
        int right = 0;
        if(root.left != null && root.val == root.left.val)
            left = leftLen + 1;
        if(root.right != null && root.val == root.right.val)
            right = rightLen + 1;
        ans = Math.max(left + right,ans);
        return Math.max(left, right);
    }
}