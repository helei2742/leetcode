package DFS.leetcode_199_二叉树的后视图;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                if(head.left != null) queue.add(head.left);
                if(head.right != null) queue.add(head.right);
                if(i == size - 1) ans.add(head.val);
            }
        }

        return ans;
    }
}