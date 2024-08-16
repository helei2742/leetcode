package hot100.leetcode_152_乘积最大子数组;

class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] minDp = new int[len], maxDp = new int[len];
        minDp[0] = maxDp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            minDp[i] = Math.min(Math.min(maxDp[i-1] * nums[i], minDp[i-1]*nums[i]), nums[i]);
            maxDp[i] = Math.max(Math.max(maxDp[i-1] * nums[i], minDp[i-1]*nums[i]), nums[i]);
        }
        int res = maxDp[0];
        for (int i = 1; i < len; i++) {
            if(maxDp[i] > res){
                res = maxDp[i];
            }
        }
        return res;
    }
}
