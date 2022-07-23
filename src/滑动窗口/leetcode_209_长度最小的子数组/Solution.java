package 滑动窗口.leetcode_209_长度最小的子数组;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;


        int l = 0, sum = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            while (sum >= target){
                ans = Math.min(ans, i-l+1);
                sum -= nums[l];
                l++;
            }
        }
        return ans == Integer.MAX_VALUE?0:ans;
    }
}
