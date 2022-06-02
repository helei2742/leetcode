package DFS.leetcode_501_二叉搜索树中的众数;

import DFS.TreeNode;

import java.util.*;

public class Solution {
    private Map<Integer, Integer> numberTimes;
    public int[] findMode(TreeNode root) {
        if (root == null) return null;
        numberTimes = new HashMap<>();
        dfs(root);
        Set<Integer> keySet = numberTimes.keySet();
        List<Integer> ansList = new ArrayList<>();
        int maxTimes = -1;
        for (Integer key : keySet) {
            Integer times = numberTimes.get(key);
            if(times > maxTimes){
                ansList.clear();
                ansList.add(key);
                maxTimes = times;
            }
            else if(times == maxTimes){
                ansList.add(key);
            }
        }
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
    private void dfs(TreeNode root) {
        if(root == null) return;
        numberTimes.put(root.val, numberTimes.getOrDefault(root.val, 0) + 1);
        dfs(root.left);
        dfs(root.right);
    }
}
