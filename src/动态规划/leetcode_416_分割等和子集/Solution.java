package 动态规划.leetcode_416_分割等和子集;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    public boolean canPartition(int[] nums) {

        int n = nums.length;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if(sum %2 !=0) return false;
        mem = new HashMap<>();
        return dfs(nums, 0, sum/2);
    }

    private Map<Integer, Boolean> mem;
    private boolean dfs(int[] nums, int index, int target){
        if(target == 0) return true;
        if(index == nums.length || target < 0) return false;
        if(mem.containsKey(index*20005 + target)) return mem.get(index*20005 + target);
        boolean f = dfs(nums,index+1,target-nums[index]) ||
                      dfs(nums, index+1, target);
        mem.put(index*20005 + target, f);
        return f;
    }
}
