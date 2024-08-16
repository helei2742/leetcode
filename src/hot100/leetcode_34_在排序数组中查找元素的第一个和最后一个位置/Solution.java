package hot100.leetcode_34_在排序数组中查找元素的第一个和最后一个位置;

import java.util.function.Function;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = bSearch(nums, midVal -> midVal > target);
        int r = bSearch(nums, midVal -> midVal >= target);
        return new int[]{l,r};
    }
    private int bSearch(int[]nums, Function<Integer, Boolean> fun){
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(fun.apply(nums[mid])){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return r+1;
    }
}
