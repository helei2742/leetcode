package 剑指offer.leetcode_剑指Offer03_数组中重复的数字;

public class Solution {
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        int[] mem = new int[len];
        for (int i = 0; i < len; i++) {
            if(++mem[nums[i]] >= 2) return nums[i];
        }
        return -1;
    }
}
