package hot100.leetcode_312_戳气球;

import java.util.Arrays;

class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int [] val = new int[len + 2];
        val[0] = 1;
        val[len+1] = 1;
        for (int i = 1; i <= len; i++) {
            val[i] = nums[i-1];
        }
        mem = new int[len+2][len+2];
        for (int i = 0; i < len+2; i++) {
            Arrays.fill(mem[i], -1);
        }
        return dfs(val, 0, len+1);
    }

    private int[][] mem;
    private int dfs(int[] nums, int left, int right) {
        if(left >= right-1){
            return 0;
        }
        if(mem[left][right] != -1) return mem[left][right];
        int res = 0;
        for (int i = left + 1; i < right; i++) {
            int sum = nums[left] * nums[i] * nums[right];
            sum += dfs(nums, left, i) + dfs(nums, i, right);
            res = Math.max(res, sum);
        }
        return mem[left][right] = res;
    }
}
