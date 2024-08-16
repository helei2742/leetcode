package hot100.leetcode_33_搜索旋转排序数组;

class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 1){
            return nums[0]==target?0:-1;
        }
        int l = 0, r = nums.length-1;
        int res = -1;
        while(l <= r){
            int mid = (r - l)/2 + l;
            if(nums[mid] == target) {
                res = mid;
                break;
            }
            if(nums[mid] >= nums[0]){
                if(nums[0] <= target && target < nums[mid]){
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }else {
                if(nums[nums.length-1] >= target && target > nums[mid]){
                    l = mid + 1;
                }else {
                    r = mid -1;
                }
            }
        }
        return res;
    }
}
