package 滑动窗口.leetcode_930_和相同的二元子数组;


import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int len = nums.length;
        Map<Integer, Integer> sumMapCount = new HashMap<>();
        int sum = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            sumMapCount.put(sum, sumMapCount.getOrDefault(sum,0));
            sum += nums[i];
            res += sumMapCount.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    private int sw(int[] nums, int goal) {
        int len = nums.length;
        int left1 = 0, left2 = 0, right = 0, res = 0;
        int sum1 = 0, sum2 = 0;
        while(right<len){
            sum1+=nums[right];
            while(left1<=right&&sum1>goal){
                sum1 -= nums[left1++];
            }
            sum2+=nums[right];
            while(left2<=right&&sum2>=goal){
                sum2 -= nums[left2++];
            }
            res += left2 - left1;
            right++;
        }
        return res;
    }
}