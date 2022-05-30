package 深度优先搜索.leetcode_783_二叉搜索树节点最小距离;

import 深度优先搜索.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int minDiffInBST(TreeNode root) {
        List<Integer> orderList = new ArrayList<>();
        inorder(root, orderList);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < orderList.size() - 1; i++) {
            int t = Math.abs(orderList.get(i)-orderList.get(i+1));
            ans = Math.min(t, ans);
        }
        return ans;
    }

    private void inorder(TreeNode root, List<Integer> orderList) {
        if(root == null) return;
        inorder(root.left, orderList);
        orderList.add(root.val);
        inorder(root.right, orderList);
    }
}
