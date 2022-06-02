package DFS.leetcode_437_路径总和;

import DFS.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    //存储前缀和 映射到 前缀和的数量
    private Map<Integer, Integer> sumToCountMap;
    private int target;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        target = targetSum;
        sumToCountMap = new HashMap<>();
        sumToCountMap.put(0,1);
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int currentSum) {
        if(root == null) return 0;
        int res = 0;
        currentSum += root.val;
       //找之前存的前缀和是否有满足 currentSum - target = 之前某点的前缀和,这样这两个点之间的值就是target
        res = sumToCountMap.getOrDefault(currentSum - target, 0);
        //记录当前前缀和的数量加1
        sumToCountMap.put(currentSum, sumToCountMap.getOrDefault(currentSum,0)+1);
        //左右递归
        int lSum = dfs(root.left, currentSum);
        int rSum = dfs(root.right, currentSum);
        //得到当前节点答案
        res = lSum + rSum +res;
        //递归返回后，去除当前前缀和，避免影响后面递归
        sumToCountMap.put(currentSum, sumToCountMap.getOrDefault(currentSum,0)-1);
        return res;
    }

}
