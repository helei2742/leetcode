package hot100.leetcode_283_移动零;

class Solution {
    public void moveZeroes(int[] nums) {
/*        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[idx++] = nums[i];
            }
        }

        for (int i = idx; i < nums.length; i++) {
            nums[i] = 0;
        }*/

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j++] = t;
            }
        }
    }

}
