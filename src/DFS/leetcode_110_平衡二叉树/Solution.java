package DFS.leetcode_110_平衡二叉树;

import DFS.TreeNode;

public class Solution {
    public boolean isBalanced(TreeNode root) {
/*        return (height(root.left)-height(root.right) <= 1)
                && isBalanced(root.left)
                && isBalanced(root.right);*/

        return height2(root) >= 0;
    }
    /*
    * 自顶向下， 每个节点都会调用一次height，o(n*2)
    * */
    private int height(TreeNode root){
        if(root == null) return 0;
        int ld = height(root.left);
        int rd = height(root.right);
        return Math.max(ld, rd) + 1;
    }

    private int height2(TreeNode root){
        if(root == null) return 0;
        int ld = height2(root.left);
        int rd = height2(root.right);
        //左右高度差大于1时返回-1代表该节点为根不是平衡的， 整个树也就是不平衡的，一直向上返回-1
        if(ld == -1 || rd == -1 || Math.abs(ld - rd) > 1){
            return -1;
        }else {
            return Math.max(ld, rd) + 1;
        }
    }
}
