package 剑指offer.leetcode_剑指Offer34二叉树中和为某一值的路径;

import 剑指offer.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        res = new ArrayList<>();
        dfs(root, new LinkedList<>(), 0, target);
        return res;
    }

    private List<List<Integer>> res ;
    private void dfs(TreeNode cur, LinkedList<Integer> path, int sum, int target){
        if(cur == null) return;
        if(sum > target) return;
        path.addLast(cur.val);
        if(sum + cur.val == target && cur.left == null && cur.right == null){
            res.add(new ArrayList<>(path));
        }
        dfs(cur.left, path, sum + cur.val, target);
        dfs(cur.right, path, sum + cur.val, target);
        path.removeLast();
    }
}
