package DFS.leetcode_538_把二叉树转化为累加树;

import DFS.TreeNode;

public class Solution {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    private void dfs(TreeNode node){
        if(node == null) return;
        dfs(node.right);
        sum += node.val;
        node.val = sum;
        dfs(node.left);
    }
}
