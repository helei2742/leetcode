package 剑指Offer二.leetcode_剑指OfferII084_含有重复元素集合的全排列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        t = new ArrayList<>();
        res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new boolean[nums.length]);
        return res;
    }

    private List<Integer> t;
    private List<List<Integer>> res;

    private void dfs(int[] nums, int count, boolean[] visited) {
        if(count == nums.length){
            res.add(new ArrayList<>(t));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i] || (i>0 && nums[i]==nums[i-1] && !visited[i-1])){
                continue;
            }
            visited[i] = true;
            t.add(nums[i]);
            dfs(nums, count+1, visited);
            t.remove(t.size()-1);
            visited[i] = false;
        }
    }
}
