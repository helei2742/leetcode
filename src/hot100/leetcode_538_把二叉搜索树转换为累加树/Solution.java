package hot100.leetcode_538_把二叉搜索树转换为累加树;

import 剑指offer.TreeNode;

class Solution {
    public TreeNode convertBST(TreeNode root) {
       dfs(root);
       return root;
    }
    private int sum = 0;
    private void dfs(TreeNode root) {
        if(root!=null){
            dfs(root.right);
            sum += root.val;
            root.val = sum;
            dfs(root.left);
        }
    }
}
