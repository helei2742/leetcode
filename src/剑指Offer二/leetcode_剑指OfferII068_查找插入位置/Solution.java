package 剑指Offer二.leetcode_剑指OfferII068_查找插入位置;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int res = r;
        while(l<=r){
            int mid = (r-l)/2 + l;
            if(target > nums[mid]) {
                l = mid + 1;
            }else {
                res = mid;
                r = mid-1;
            }
        }
        return res;
    }
}