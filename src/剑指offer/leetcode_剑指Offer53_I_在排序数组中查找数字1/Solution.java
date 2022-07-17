package 剑指offer.leetcode_剑指Offer53_I_在排序数组中查找数字1;

import java.util.function.Function;

public class Solution {

    public int search(int[] nums, int target) {
        int len = nums.length;
        int right = bSearch(nums, mid -> {
            return nums[mid] > target;
        })-1;
        int left = bSearch(nums, mid->{
            return nums[mid] >= target;
        });
        if (left <= right && right < len && nums[left] == target && nums[right] == target) {
            return right - left + 1;
        }
        return 0;
    }

    private int bSearch(int[] nums, Function<Integer,Boolean> midBigger){
        int left = 0, right = nums.length - 1;
        int res = nums.length;
        while (left<=right){
            int mid = (left+right)/2;
            if(midBigger.apply(mid)){
                right = mid - 1;
                res = mid;
            }else {
                left = mid + 1;
            }
        }
        return res;
    }
}
