package hot100.leetcode_287_寻找重复数;

class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int res = 0;

        while(l <= r){
            int mid = (l+r)>>1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if(nums[i] <= mid) count++;
            }
            if(count >= mid){
                res = mid;
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return res;
    }
}
