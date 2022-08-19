package 剑指Offer二.leetcode_剑指OfferII049_从根节点到叶节点的路径数字之和;

import 剑指offer.TreeNode;

class Solution {
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private int sum = 0;
    private void dfs(TreeNode root, int pathTot) {
        if(root == null) return;
        if(root.left == null && root.right == null){
            sum += pathTot + root.val;
        }
        dfs(root.left, pathTot + root.val);
        dfs(root.right, pathTot + root.val);
    }
}
