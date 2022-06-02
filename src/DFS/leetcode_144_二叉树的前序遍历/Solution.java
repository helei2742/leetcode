package DFS.leetcode_144_二叉树的前序遍历;

import DFS.TreeNode;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);
        return ans;
    }
    private void preorder(TreeNode root, List<Integer> list){
        if(root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }
}