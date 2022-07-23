package 剑指offer.leetcode_剑指Offer56II_数组中数字出现的次数II;


import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {
        int[] count = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num>>>=1;
            }
        }
        System.out.println(Arrays.toString(count));

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res |= count[31-i]%3;
            res <<=1;
        }
        return res;
    }
}