package 剑指Offer二.leetcode_剑指OfferII081_允许重复选择元素的组合;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        t = new ArrayList<>();
        res = new ArrayList<>();

        dfs(candidates, 0, 0, target);
        return res;
    }

    private List<Integer> t;
    private List<List<Integer>> res;
    private void dfs(int[] nums, int idx, int current, int target) {
        if(current >= target) {
            if(current == target)
                res.add(new ArrayList<>(t));
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            t.add(nums[i]);
            dfs(nums, i,current + nums[i], target);
            t.remove(t.size()-1);
        }
    }
}
