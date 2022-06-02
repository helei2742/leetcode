package DFS.leetcode_660_修剪二叉搜索树;

import DFS.TreeNode;

public class Solution {



    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root,low,high);
    }

    /*
        利用二叉搜索树的性质，
        从下向上删，
        一个节点值符合直接返回， 向下递归时处理子节点

        节点不符合时，看它的子节点是否符合。（从下往上，子节点一定是符合的）
            1、关键是看右子节点，因为右子节点的值一定比左子节点大
            将这两个子节点为根的树拼接到一起只能将左子树的根接到右子树最底层的最左边
            2、右子树为空时直接返回左子树
     */
    private TreeNode dfs(TreeNode root, int l, int h) {
        if(root == null) return null;

        root.left = dfs(root.left, l, h);
        root.right = dfs(root.right, l, h);

        if(root.val >=l && root.val <= h){
            return root;
        }else {
            if(root.right == null){
                return root.left;
            }

            TreeNode temp = root.right;
            while(temp.left!=null){
                temp = temp.left;
            }
            temp.left = root.left;
            root.left = null;
            return root.right;
        }
    }
}
