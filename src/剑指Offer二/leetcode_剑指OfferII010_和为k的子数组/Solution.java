package 剑指Offer二.leetcode_剑指OfferII010_和为k的子数组;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        int preSum = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            preSum += nums[i];
            if(preSumMap.containsKey(preSum - k)){
                res += preSumMap.get(preSum - k);
            }
            preSumMap.put(preSum, preSumMap.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }
}