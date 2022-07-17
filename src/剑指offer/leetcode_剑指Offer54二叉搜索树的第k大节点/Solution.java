package 剑指offer.leetcode_剑指Offer54二叉搜索树的第k大节点;

import 剑指offer.TreeNode;

class Solution {
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    int count = 0;
    int k = 0;
    private int ans = 0;
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.right);

        if(++count==k) {
            ans = root.val;
            return;
        }else if(count > k){
            return;
        }
        dfs(root.left);

    }
}