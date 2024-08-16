package hot100.leetcode_75_颜色分类;

class Solution {
    public void sortColors(int[] nums) {
        int[] c = new int[3];
        for (int i = 0; i < nums.length; i++) {
            c[nums[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            while(c[i]-- > 0){
                nums[idx++] = i;
            }
        }
    }
}
