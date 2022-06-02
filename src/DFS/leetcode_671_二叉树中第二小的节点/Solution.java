package DFS.leetcode_671_二叉树中第二小的节点;

import DFS.TreeNode;

public class Solution {
    private int rootVal = -1;
    private int ans = -1;
    /*
    树根为最小，沿着树根找第二小即可
     */
    public int findSecondMinimumValue(TreeNode root) {
        rootVal = root.val;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root){
        if(root==null) return;

        if(ans!=-1&&root.val>ans) return;

        if(root.val>rootVal) ans = root.val;

        dfs(root.left);
        dfs(root.right);
    }
}
