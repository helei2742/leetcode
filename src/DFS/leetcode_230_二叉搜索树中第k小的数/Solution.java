package DFS.leetcode_230_二叉搜索树中第k小的数;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    private List<Integer> list;
    public int kthSmallest(TreeNode root, int k) {
        list = new ArrayList<>();
        inorder(root);
        return list.get(k-1);
    }

    private void inorder(TreeNode root) {
        if(root != null) {
            inorder(root.left);
            list.add(root.val);
            inorder(root.right);
        }
    }

    private int getKthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(true){
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.getLast();
            if(--k == 0) return root.val;
            root = root.right;
        }
    }
}
