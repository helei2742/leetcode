package hot100.leetcode_31_下一个排列;

class Solution {
    public void nextPermutation(int[] nums) {
        // 4 3 2 6 3 4 5
        int i = nums.length - 2;
        while (i>=0 && nums[i] >= nums[i+1]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            while(j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            System.out.println(i+"--"+j);
            swap(nums, i, j);
        }
        reverse(nums, i+1, nums.length);
    }
    private void swap(int[]nums, int i, int j){
        nums[i] += nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
    private void reverse(int[]nums, int start, int end){
        while(start < end){
            swap(nums, start++, end--);
        }
    }
}
