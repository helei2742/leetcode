package 滑动窗口.leetcode_219_存在重复元素II;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        if(len < k) return false;

        for (int i = 0; i < len-k; i++) {
            for (int j = i+1; j<=i+k&&j<len; j++) {
                if(nums[j]==nums[i]) return true;
            }
        }
        return false;
    }

    private boolean sw(int[] nums, int k){
        int len = nums.length;
        Set<Integer> set = new HashSet<>(len);

        for (int i = 0; i < len; i++) {
            if(i>k){
                set.remove(nums[i-k-1]);
            }
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
