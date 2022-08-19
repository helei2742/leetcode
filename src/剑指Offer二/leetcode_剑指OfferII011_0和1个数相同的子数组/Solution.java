package 剑指Offer二.leetcode_剑指OfferII011_0和1个数相同的子数组;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        int len = nums.length;

        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, -1);

        int preSum = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i] == 0 ? -1 : nums[i];
            preSum += num;
            if(preSumMap.containsKey(preSum)){
                res = Math.max(res, i - preSumMap.get(preSum));
            }
            else
                preSumMap.put(preSum, i);
        }
        return res;
    }
}
