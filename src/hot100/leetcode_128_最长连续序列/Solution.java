package hot100.leetcode_128_最长连续序列;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int longestConsecutive_2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;

        for (int num : nums) {
            if(!set.contains(num-1)) {
                int curLen = 1;
                int curVal = num;
                while(set.contains(curVal+1)){
                    curLen++;
                    curVal++;
                }
                res = Math.max(res, curLen);
            }
        }
        return res;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;
        for (int num : nums) {
            if(set.contains(num-1)) continue;
            int curLen = 1;
            int curValue = num;
            while (set.contains(curValue + 1)) {
                curValue++;
                curLen++;
            }
            res = Math.max(curLen, res);
        }
        return res;
    }
}
