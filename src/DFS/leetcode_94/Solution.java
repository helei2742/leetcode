package DFS.leetcode_94;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final List<Integer> order = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        order.clear();
        inorder(root);
        return order;
    }
    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        order.add(root.val);
        inorder(root.right);
    }
}