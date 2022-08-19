package 剑指Offer二.leetcode_剑指OfferII012_左右两边子数组的和相等;

class Solution {
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }

        for (int i = 0; i < len; i++) {
            int left = preSum[i] - nums[i];
            int right = preSum[len-1] - preSum[i];
            if(left == right) return i;
        }
        return -1;
    }
}
