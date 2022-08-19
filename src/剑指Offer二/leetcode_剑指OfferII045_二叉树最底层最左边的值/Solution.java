package 剑指Offer二.leetcode_剑指OfferII045_二叉树最底层最左边的值;

import 剑指offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        if(root == null) return -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(i == 0) res = poll.val;
                if(poll.left!=null) queue.add(poll.left);
                if(poll.right!=null) queue.add(poll.right);
            }
        }
        return res;
    }
}