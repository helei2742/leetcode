package 剑指Offer二.leetcode_剑指OfferII082_含有重复元素集合的组合;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        t=new ArrayList<>();
        res = new ArrayList<>();
        Arrays.sort(candidates);

        dfs(candidates, 0,0,target);
        return res;
    }
    private List<Integer> t;
    private List<List<Integer>> res;
    private void dfs(int[] nums, int idx, int count, int target) {
        if(count>=target){
            if(count==target){
                res.add(new ArrayList<>(t));
            }
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) { // 剪枝、避免重复
                // 因为前面那个同样的数已经经历过dfs，再拿同样的数dfs就会得到重复的答案
                continue;
            }
            t.add(nums[i]);
            dfs(nums, i+1, count+nums[i], target);
            t.remove(t.size()-1);
        }
    }
}
