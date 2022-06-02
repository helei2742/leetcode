package DFS.leetcode_404_左叶子之和;

import DFS.TreeNode;

public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int rVal = dfs(root.right);
        int lVal = 0;
        if(isLeaf(root.left)) lVal = root.left.val;
        else lVal = dfs(root.left);
        return lVal + rVal;
    }

    private boolean isLeaf(TreeNode treeNode) {
        return treeNode !=null && treeNode.left == null && treeNode.right == null;
    }
}
