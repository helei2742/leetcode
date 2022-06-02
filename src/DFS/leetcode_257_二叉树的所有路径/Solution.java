package DFS.leetcode_257_二叉树的所有路径;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> ans = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return ans;
    }
    private void dfs(TreeNode root, String path) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            ans.add(path + root.val);
        }
        dfs(root.left, path + root.val + "->");
        dfs(root.right, path + root.val + "->");
    }
}
