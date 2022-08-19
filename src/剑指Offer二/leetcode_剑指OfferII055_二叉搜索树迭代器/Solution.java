package 剑指Offer二.leetcode_剑指OfferII055_二叉搜索树迭代器;

import 剑指offer.TreeNode;

import java.util.ArrayList;
import java.util.List;

class BSTIterator {

    private List<Integer> list = new ArrayList<>();
    private int index = 0;
    public BSTIterator(TreeNode root) {
        inorderTraversal(root, list);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index<=list.size();
    }
    private void inorderTraversal(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, arr);
        arr.add(root.val);
        inorderTraversal(root.right, arr);
    }
}
