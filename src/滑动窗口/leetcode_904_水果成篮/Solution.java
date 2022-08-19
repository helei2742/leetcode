package 滑动窗口.leetcode_904_水果成篮;


class Solution {
    public int totalFruit(int[] fruits) {
        int len = fruits.length;
        int left = 0, right = 0;
        int cur = 0, limit = 2;

        int[] count = new int[len];
        int res = 0;
        while (right<len){
            int rVal = fruits[right];
            right++;
            if(count[rVal]!=0){
                count[rVal]++;
            }else if(cur < limit){
                count[rVal]++;
                cur++;
            }else {
                while (cur >= limit){
                    if(--count[fruits[left++]]==0){
                        cur--;
                    }
                }
                right--;
            }
            res = Math.max(res, right-left);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0,1,1,4,3};
        solution.totalFruit(arr);
    }
}
