package hot100.leetcode_1_两数之和;

import java.util.*;


class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valueIdxMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(valueIdxMap.containsKey(target - num)) {
                return new int[]{i, valueIdxMap.get(num)};
            }
            valueIdxMap.put(num, i);
        }

        return new int[]{};
    }
}
