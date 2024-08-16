package hot100.leetcode_78_子集;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        t = new ArrayList<>();
        res = new ArrayList<>();
        dfs(nums, 0);
        return res;
    }

    List<Integer> t;
    List<List<Integer>> res;
    private void dfs(int[] nums, int idx){
        if(idx == nums.length){
            res.add(new ArrayList<>(t));
            return;
        }

        t.add(nums[idx]);
        dfs(nums, idx+1);
        t.remove(t.size()-1);
        dfs(nums, idx+1);
    }
}
