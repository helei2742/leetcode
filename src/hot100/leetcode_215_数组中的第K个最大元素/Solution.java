package hot100.leetcode_215_数组中的第K个最大元素;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        this.k = k-1;

        qs(nums, 0, len-1);
        return res;
    }
    private int k;
    private int res;
    private void qs(int[] nums, int left, int right) {
        int l = left, r = right;
        int mid =  nums[(r+l)/2];
        while(l<=r){
            while(nums[l] >mid){
                l++;
            }
            while(nums[r] < mid){
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
            qs(nums, left, r);
        }else if(k >= l){
            qs(nums, l, right);
        }else {
            res = nums[r+1];
        }
    }
}
