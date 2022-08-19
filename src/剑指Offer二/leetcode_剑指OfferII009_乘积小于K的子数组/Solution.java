package 剑指Offer二.leetcode_剑指OfferII009_乘积小于K的子数组;

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = 0;
        int tot = 1;
        int res = 0;
        while(right<len){
            tot *= nums[right];
            right++;
            while(left<right&&tot >= k){
                tot /= nums[left];
                left++;
            }
            res += right - left;
        }
        return res;
    }
}
