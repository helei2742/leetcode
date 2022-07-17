package 剑指offer.leetcode_剑指Offer57和为s的两个数字;


import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
/*        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if(set.contains(target - num)) return new int[]{num, target-num};
            set.add(num);
        }
        return new int[0];*/
        //节约空间双指针,因为有序，每次判断两边指针所指和与目标大小，调整指针位置即可

        int l = 0, r = nums.length-1;

        while (l <= r){
            if(nums[l] == target - nums[r]) return new int[]{nums[l], nums[r]};
            else if(nums[l] > target - nums[r]){
                r--;
            }else {
                l++;
            }
        }
        return new int[0];
    }
}
