package DFS.leetcode_337_打家劫舍;

import DFS.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<TreeNode, Integer> select;
    private Map<TreeNode, Integer> noSelect;

    public int rob(TreeNode root) {
        if (root == null) return 0;

        select = new HashMap<>();
        noSelect = new HashMap<>();

        dfs(root);
        return Math.max(select.get(root), noSelect.get(root));
    }

    private void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        //选当前节点
        int selectCurrent = root.val +
                noSelect.getOrDefault(root.left, 0) +
                noSelect.getOrDefault(root.right, 0);
        //不选当前节点
        int noSelectCurrent = Math.max(
                select.getOrDefault(root.left,0),noSelect.getOrDefault(root.left,0))
                + Math.max(
                select.getOrDefault(root.right,0),noSelect.getOrDefault(root.right,0));

        select.put(root, selectCurrent);
        noSelect.put(root, noSelectCurrent);
    }
}
