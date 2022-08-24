package 剑指Offer二.leetcode_剑指OfferII070_排序数组中只出现一次的数字;

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        int len = nums.length;
        int res = 0;
        while(l <= r) {
            int mid = l + (r-l)/2;
            if(mid+1<len&&nums[mid] == nums[mid+1]){
                if(mid%2==0){
                    l = mid + 2;
                }else {
                    r = mid - 1;
                }
            }else if(mid>0&&nums[mid] == nums[mid-1]){
                if(mid%2==0){
                    r = mid - 2;
                }else {
                    l = mid + 1;
                }
            }else {
                res = mid;
                break;
            }
        }
        return nums[res];
    }
}
