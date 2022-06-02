package DFS.leetcode_652_寻找相同子树;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<String, Integer> count;
    private List<TreeNode> ans;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        count = new HashMap<>();
        ans = new ArrayList<>();

        listTree(root);

        return ans;
    }

    private String listTree(TreeNode root) {
        if(root == null) return "#";
        String t = root.val + "," + listTree(root.left) +","+listTree(root.right);
        count.put(t, count.getOrDefault(t, 0) + 1);
        if(count.get(t) == 2)
            ans.add(root);
        return t;
    }
}
