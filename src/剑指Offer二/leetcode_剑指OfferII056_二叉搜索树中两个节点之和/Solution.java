package 剑指Offer二.leetcode_剑指OfferII056_二叉搜索树中两个节点之和;

import 剑指offer.TreeNode;

import java.util.*;

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        this.k = k;
        dfs(root);
        return res;
    }

    private Set<Integer> set;
    private int k;
    private boolean res;
    private void dfs(TreeNode root) {
        if(root == null) return;

        if(set.contains(k-root.val)){
            res = true;
            return;
        }
        set.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if(root != null){
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
    private boolean fn(TreeNode root, int k){
        List<Integer> list = new ArrayList<>();

        inorder(root, list);
        int left = 0, right = list.size() - 1;
        boolean res = false;
        while(left < right){
            int lV = list.get(left);
            int lR = list.get(right);
            if(lV + lR > k){
                right--;
            }else if(lV + lR < k){
                left++;
            }else {
                res = true;
                break;
            }
        }
        return res;
    }
}