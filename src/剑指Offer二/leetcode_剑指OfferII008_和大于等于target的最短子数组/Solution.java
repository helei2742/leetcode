package 剑指Offer二.leetcode_剑指OfferII008_和大于等于target的最短子数组;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            while(sum >= target){
                res = Math.min(res, i-left+1);
                sum -= nums[left];
                left++;
            }
        }
        return res==Integer.MAX_VALUE?0:res;
    }
}

