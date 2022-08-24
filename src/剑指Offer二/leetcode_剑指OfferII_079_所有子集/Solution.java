package 剑指Offer二.leetcode_剑指OfferII_079_所有子集;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        len = nums.length;
        dfs(nums, 0, new LinkedList<>());
        return res;
    }

    List<List<Integer>> res;
    int len;
    private void dfs(int[] nums, int idx, LinkedList<Integer> list) {
        if(idx == len) {
            res.add(new ArrayList<>(list));
        }

        list.addLast(nums[idx]);
        dfs(nums, idx+1, list);
        list.removeLast();
        dfs(nums, idx+1, list);
    }
}
