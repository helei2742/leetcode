package 深度优先搜索.leetcode_450_删除二叉搜索树中的节点;

import 深度优先搜索.TreeNode;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        return dfs(root, key);
    }
    //从叶开始
    private TreeNode dfs(TreeNode root, int key) {
        if(root == null) return null;

        root.left = dfs(root.left, key);
        root.right = dfs(root.right, key);

        if(root.val != key){
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
    private TreeNode dfsDeleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else {
            //存在右边的节点，删去该点后，可用该点右子树的最左边的节点代替（搜索树的性质)
            if(root.left != null && root.right != null){
                TreeNode leftNodeOfRight = findLeftNodeOfRight(root);
                leftNodeOfRight.left = root.left;
                return root.right;
            }
            if (root.right != null) {
                return root.right;//删除节点，右孩子补位 ，返回右孩子为根节点
            }else {
                return root.left;//删除节点，左孩子补位，返回左孩子为根节点
            }
        }
        return root;
    }

    private TreeNode findLeftNodeOfRight(TreeNode root) {
        TreeNode temp = root.right;
        while(temp.left != null)
            temp = temp.left;
        return temp;
    }
}