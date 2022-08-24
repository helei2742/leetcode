package 剑指Offer二.leetcode_剑指OfferII076_数组中的第k大的数字;

import java.util.Arrays;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        this.k = k;
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        return res;
    }

    int res;
    int k;
    private void quickSort(int[] nums, int left, int right){
        int l = left, r = right;
        int flag = nums[(l+r)/2];
        while (l<=r){
            while(nums[l] > flag){
                l++;
            }
            while(nums[r] < flag){
                r--;
            }
            if(l<=r){
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
                l++;
                r--;
            }
        }

        if(k <= r){
            quickSort(nums, left, r);
        }else if(k >= l){
            quickSort(nums, l, right);
        }else {
            res = nums[r+1];
        }
    }
}
