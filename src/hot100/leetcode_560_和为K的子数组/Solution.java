package hot100.leetcode_560_和为K的子数组;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
/*
        int len = nums.length;
        int[] pre = new int[len];
        pre[0] = nums[0];
        for (int i = 1; i < len; i++) {
            pre[i] = pre[i-1] + nums[i];
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if(pre[i] - pre[j] + nums[j] == k){
                    res++;
                }
            }
        }
        return res;
*/


        int res = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if(map.containsKey(preSum - k)){
                res += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }
}
