package 动态规划.leetcode_152_乘积最大的子数组;

import java.util.Map;

public class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];

        int[] maxDp = new int[n];
        int[] minDp = new int[n];

        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            maxDp[i] = Math.max(Math.max(nums[i], maxDp[i-1] * nums[i]),minDp[i-1]*nums[i]);
            minDp[i] = Math.min(Math.min(nums[i], minDp[i-1]*nums[i]), maxDp[i-1]*nums[i]);
        }
        int ans = maxDp[0];
        for (int i = 1; i < maxDp.length; i++) {
            ans = Math.max(ans, maxDp[i]);
        }

        return ans;
    }
}
