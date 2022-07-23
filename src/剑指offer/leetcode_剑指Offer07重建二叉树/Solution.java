package 剑指offer.leetcode_剑指Offer07重建二叉树;

import 剑指offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder,0, len-1, inorder, 0, len-1);
    }

    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight){
        if(preLeft > preRight){
            return null;
        }

        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);

        int rootIdx = map.get(rootValue);

        TreeNode left = buildTree(preorder, preLeft + 1, preLeft + rootIdx - inLeft,
                inorder, inLeft, rootIdx - 1);
        TreeNode right = buildTree(preorder, preLeft + rootIdx - inLeft + 1, preRight,
                inorder, rootIdx + 1, inRight);
        root.left = left;
        root.right = right;
        return root;
    }
}
