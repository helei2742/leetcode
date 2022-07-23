package 剑指offer.leetcode_剑指Offer33_二叉搜索树的后序遍历序列;

public class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return fn(postorder, 0, postorder.length-1);
    }
    private boolean fn(int[] postorder, int l, int r){
        if(l>=r) return true;

        int idx = l;
        while (postorder[idx] < postorder[r]) idx++;
        int flag = idx;
        while (postorder[idx] > postorder[r]) idx++;

        return r==idx && fn(postorder, l, flag-1) && fn(postorder, flag, r-1);
    }
}
