package DFS.leetcode_530_二叉树的最小绝对差;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> list;
    public int getMinimumDifference(TreeNode root) {
        list = new ArrayList<>();
        dfs(root);
        int minAbs = Integer.MAX_VALUE;
        int size = list.size();
        for (int i = 1; i < size; i++) {
            minAbs = Math.min(Math.abs(list.get(i - 1) - list.get(i)), minAbs);
        }
        return minAbs;
    }

    private void dfs(TreeNode root) {
        if(root != null){
            dfs(root.left);
            list.add(root.val);
            dfs(root.right);
        }
    }
}
