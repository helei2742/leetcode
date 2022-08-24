package 剑指Offer二.leetcode_剑指OfferII083_没有重复元素集合的全排列;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        t = new ArrayList<>();
        res = new ArrayList<>();
        dfs(nums, 0, new boolean[nums.length]);
        return res;
    }

    private List<Integer> t;
    private List<List<Integer>> res;
    private void dfs(int[] nums, int count, boolean[] visited) {
        if(count == nums.length) {
            res.add(new ArrayList<>(t));
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visited[i]){
                t.add(nums[i]);
                visited[i] = true;
                dfs(nums,count+1, visited);
                visited[i] = false;
                t.remove(t.size()-1);
            }
        }
    }
}
