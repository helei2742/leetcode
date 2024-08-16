package hot100.leetcode_46_全排列;

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
    private void dfs(int[] nums, int count, boolean[] isVisited) {
        if(count == nums.length){
            res.add(new ArrayList<>(t));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!isVisited[i]){
                isVisited[i] = true;
                t.add(nums[i]);
                dfs(nums, count+1, isVisited);
                isVisited[i] = false;
                t.remove(t.size()-1);
            }
        }
    }
}
