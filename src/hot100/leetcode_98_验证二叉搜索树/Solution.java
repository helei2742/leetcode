package hot100.leetcode_98_验证二叉搜索树;

import 剑指offer.TreeNode;

import java.util.Stack;

class Solution {
    public boolean isValidBST(TreeNode root) {
        return fn(root);

    }
    private boolean dfs(TreeNode node, long min, long max) {
        if(node == null) return true;
        if(node.val <= min || node.val >= max) return false;
        return dfs(node.left, min, node.val) && dfs(node.right, node.val, max);
    }
    private boolean fn(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        while(!stack.isEmpty() || root != null){
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.val <= pre) {
                return false;
            }
            pre = root.val;

            root = root.right;
        }
        return true;
    }
}
