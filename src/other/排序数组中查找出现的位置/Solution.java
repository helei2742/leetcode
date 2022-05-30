package other.排序数组中查找出现的位置;

import java.util.function.Predicate;

public class Solution {

    public static int[] func(int[] nums, int target){
        int lIdx = bFind(nums, (i)->{
            return target <= i;
        });
        int rIdx = bFind(nums, (i)->{
            return target < i;
        });
        System.out.println(lIdx+"=="+rIdx);
        return null;
    }
    private static int bFind(int[] nums, Predicate<Integer> predicate){
        int left = 0, right = nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(predicate.test(nums[mid])){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return right+1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,2,2,2,4};
        func(arr, 2);
    }
}
