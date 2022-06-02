package 并查集.leetcode_128_最长连续序列;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLength = 0;
        for (Integer num : numSet) {
            if(!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 0;
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                maxLength = Math.max(currentLength, maxLength);
            }
        }
        return maxLength;
    }
}
