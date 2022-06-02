package DFS.leetcode_129_根节点到叶节点数字之和;

import DFS.TreeNode;

public class Solution {

    public int sumNumbers(TreeNode root) {

        dfs(root, 0);
        return sum;
    }

    private int sum = 0;
    private void dfs(TreeNode root, int pathValue) {
        if(root == null) return;
        if(root.right == null && root.left == null){
            sum += pathValue * 10 + root.val;
            return;
        }
        if(root.left != null){
            dfs(root.left, pathValue * 10 + root.val);
        }
        if(root.right != null){
            dfs(root.right, pathValue * 10 +root.val);
        }
    }
}
