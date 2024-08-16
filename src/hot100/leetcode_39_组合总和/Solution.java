package hot100.leetcode_39_组合总和;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        t = new ArrayList<>();
        res = new ArrayList<>();

        dfs(candidates,0, 0, target);
        return res;
    }
    private List<Integer> t;
    private List<List<Integer>> res;
    private void dfs(int[] candidates, int idx, int count, int target){
        if(count > target) return;
        if(count == target) {
            res.add(new ArrayList<>(t));
        }

        for (int i = idx; i < candidates.length; i++) {
            t.add(candidates[i]);
            dfs(candidates, i, count+candidates[i], target);
            t.remove(t.size()-1);
        }
    }
}
