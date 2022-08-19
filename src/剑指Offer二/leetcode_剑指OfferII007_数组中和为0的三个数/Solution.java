package 剑指Offer二.leetcode_剑指OfferII007_数组中和为0的三个数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if(i>0 && nums[i-1] == nums[i]) continue;
            int a = nums[i];
            int left = i + 1, right = len - 1;
            while(left<right){
                int sum = a+nums[left]+nums[right];
                if(sum == 0){
                    res.add(Arrays.asList(a, nums[left], nums[right]));
                    while(left<right && nums[left] == nums[left+1]) left++;
                    while(left<right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }else if(sum < 0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }
}
