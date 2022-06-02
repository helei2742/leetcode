package DFS.leetcode_98_验证二叉搜索树;

public class Solution {
    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return Dfs(root);
    }

    private boolean Dfs(TreeNode root) {
        if(root == null) return true;
        boolean left = Dfs(root.left);
        if(root.val <= pre) return false;
        pre = root.val;
        boolean right = Dfs(root.right);
        return left&&right;
    }

    private boolean Dfs1(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return Dfs1(root, min, root.val) && Dfs1(root, root.val, max);
    }
}
