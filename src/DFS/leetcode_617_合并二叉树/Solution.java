package DFS.leetcode_617_合并二叉树;

import DFS.TreeNode;

public class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }
    private TreeNode dfs(TreeNode root1, TreeNode root2){
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        TreeNode temp = new TreeNode(root1.val+ root2.val);
        TreeNode lNode = dfs(root1.left, root2.left);
        TreeNode rNode = dfs(root1.right, root2.right);
        temp.left = lNode;
        temp.right = rNode;
        return temp;
    }
}
