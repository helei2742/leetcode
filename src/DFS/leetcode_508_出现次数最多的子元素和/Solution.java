package DFS.leetcode_508_出现次数最多的子元素和;

import DFS.TreeNode;

import java.util.*;

public class Solution {
    private Map<Integer, Integer> sumToTimes;

    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return null;

        sumToTimes = new HashMap<>();
        dfs(root);
        Set<Integer> sums = sumToTimes.keySet();
        List<Integer> ansList = new ArrayList<>();

        int maxTimes = -1;
        for (Integer sum : sums) {
            Integer times = sumToTimes.get(sum);
            if(times > maxTimes){
                maxTimes = times;
                ansList.clear();
                ansList.add(sum);
            }else if(times == maxTimes) {
                ansList.add(sum);
            }
        }
        return ansList.stream().mapToInt((Integer::intValue)).toArray();
    }

    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int lVal = 0, rVal = 0;
        if(root.left != null)
            lVal = dfs(root.left);
        if(root.right != null)
            rVal = dfs(root.right);

        int re = lVal + rVal + root.val;
        sumToTimes.put(re, sumToTimes.getOrDefault(re, 0)+1);
        return re;
    }
}
