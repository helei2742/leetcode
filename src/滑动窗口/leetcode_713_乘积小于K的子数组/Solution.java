package 滑动窗口.leetcode_713_乘积小于K的子数组;

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0, mul = 1;
        int ans = 0;
        while(right < n){
            mul *= nums[right];
            right++;
            while (left<right&&mul >= k){
                mul /= nums[left];
                left++;
            }
            ans += right-left;
        }
        return ans;
    }
}