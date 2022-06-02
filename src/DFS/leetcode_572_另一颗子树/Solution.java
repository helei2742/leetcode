package DFS.leetcode_572_另一颗子树;

import DFS.TreeNode;

class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;
        if(subRoot == null) return true;
        return dfs(root, subRoot) ||
                isSubtree(root.right,subRoot) ||
                isSubtree(root.left,subRoot);
    }

    /**
     * 判断两树是否相同
     * @param root
     * @param target
     * @return
     */
    public boolean dfs(TreeNode root, TreeNode target){
        if(root == null && target == null) return true;
        if(root == null || target == null) return false;
        if(root.val != target.val) return false;
        return dfs(root.left, target.left) && dfs(root.right, target.right);
    }
}