package DFS.leetcode_563_二叉树的坡度;

import DFS.TreeNode;

public class Solution {
    public int findTilt(TreeNode root) {
        dfs(root);
        return ans;
    }
    private int ans;
    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int lSum = dfs(root.left);
        int rSUm = dfs(root.right);
        ans += Math.abs(lSum - rSUm);
        return lSum + root.val + rSUm;
    }
}
