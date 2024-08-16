package hot100.leetcode_437_路径总和III;

import 剑指offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {

    private Map<Long, Integer> sumCountMap;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        sumCountMap = new HashMap<>();
        this.target = targetSum;
        sumCountMap.put(0l,1);
        return dfs(root, 0);
    }
    private int target;
    private int dfs(TreeNode root, long preSum) {
        if(root == null) return 0;
        int res = 0;
        preSum += root.val;
        res += sumCountMap.getOrDefault(preSum - target, 0);
        sumCountMap.put(preSum, sumCountMap.getOrDefault(preSum, 0)+1);

        int left = dfs(root.left, preSum);
        int right = dfs(root.right, preSum);
        res += left + right;
        sumCountMap.put(preSum, sumCountMap.getOrDefault(preSum, 0)-1);
        return res;
    }
}
