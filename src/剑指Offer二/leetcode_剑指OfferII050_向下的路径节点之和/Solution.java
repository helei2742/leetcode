package 剑指Offer二.leetcode_剑指OfferII050_向下的路径节点之和;

import 剑指offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, 0);
    }
    private int target;
    private Map<Integer, Integer> map = new HashMap<>();
    private int dfs(TreeNode root, int preSum){
        if(root == null) return 0;
        preSum += root.val;
        int res = 0;
        res += map.getOrDefault(preSum - target, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        int left = dfs(root.left, preSum);
        int right = dfs(root.right, preSum);
        res += left + right;
        map.put(preSum, map.getOrDefault(preSum, 0) -1);
        return res;
    }
}
