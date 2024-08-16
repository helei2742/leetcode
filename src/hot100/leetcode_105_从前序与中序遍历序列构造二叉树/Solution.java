package hot100.leetcode_105_从前序与中序遍历序列构造二叉树;

import 剑指offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    private Map<Integer, Integer> map;
    private TreeNode build(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight){
        if(preLeft > preRight) return null;
        int rootVal = preorder[preLeft];
        int rootValIdx = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preLeft+1, preLeft+rootValIdx-inLeft,
                inorder, inLeft, rootValIdx-1);
        root.right = build(preorder, preLeft+rootValIdx-inLeft+1, preRight,
                inorder, rootValIdx+1, inRight);
        return root;
    }
}
