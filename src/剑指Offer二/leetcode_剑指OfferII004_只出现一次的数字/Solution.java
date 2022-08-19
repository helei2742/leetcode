package 剑指Offer二.leetcode_剑指OfferII004_只出现一次的数字;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            if (map.get(nums[i])==1){
                return nums[i];
            }
        }
        return 0;
    }
}