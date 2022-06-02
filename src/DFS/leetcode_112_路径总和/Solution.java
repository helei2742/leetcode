package DFS.leetcode_112_路径总和;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private int sum;
    List<List<Integer>> ans = new ArrayList<>();
    public boolean hasPathSum(TreeNode root, int targetSum) {
        sum = targetSum;
        return dfs(root, 0);
    }

    private boolean dfs(TreeNode root, int value) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            return value + root.val == sum;
        }
        return dfs(root.left, value + root.val)
                || dfs(root.right, value + root.val);
    }


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        sum = targetSum;
        List<Integer> list = new ArrayList<>();
        dfs2(root, 0, new ArrayList<>());
        return ans;
    }
    private void dfs2(TreeNode root, int value, List<Integer> path) {
        if(root == null) return;
        if(root.left == null && root.right == null && value + root.val == sum){
            path.add(root.val);
            ans.add(path);
        }
        if(root.left != null) {
            List<Integer> list = new ArrayList<>(path);
            list.add(root.val);
            dfs2(root.left, value + root.val, list);
        }
        if(root.right != null) {
            List<Integer> list = new ArrayList<>(path);
            list.add(root.val);
            dfs2(root.right, value + root.val, list);
        }
    }
}
