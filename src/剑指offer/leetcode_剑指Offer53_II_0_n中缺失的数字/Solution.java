package 剑指offer.leetcode_剑指Offer53_II_0_n中缺失的数字;

class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length-1;

        while (l<=r){
            int mid = (l+r)/2;
            if(nums[mid]!=mid){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return r;
    }
}
