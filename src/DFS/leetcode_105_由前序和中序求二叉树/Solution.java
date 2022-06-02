package DFS.leetcode_105_由前序和中序求二叉树;

import DFS.TreeNode;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return rebuildTree(preorder,inorder,0,0,inorder.length-1);
    }
    public TreeNode rebuildTree(int[] preorder, int[] inorder,
                                int preStart, int inStart,int inEnd) {

        if(inStart > inEnd) return null;
        int value = preorder[preStart];
        TreeNode root = new TreeNode(value);

        int inIndex = 0;
        for(int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == value) {
                inIndex = i;
            }
        }
        
        TreeNode left = rebuildTree(preorder,inorder,preStart+1,inStart,inIndex-1);
        TreeNode right = rebuildTree(preorder,inorder,preStart+inIndex-inStart+1,inIndex+1,inEnd);

        root.left = left;
        root.right = right;
        return root;
    }
}
