package 动态规划.letcode_674_最长连续递增序列;

public class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int left = 0, right = 0;
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[left] < nums[right]){
                while(right<nums.length&&nums[right]>nums[right-1]){
                    right++;
                    res = Math.max(res, right-left+1);
                }
            }else{
                left = right;
                right++;
            }
        }
        return res;
    }

    private int fn(int[] nums){
        int res = 0;
        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            if(i>0&&nums[i]<=nums[i-1]){
                start=i;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}
