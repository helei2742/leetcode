package 剑指offer.leetcode_剑指Offer56I_数组中数字出现的次数;

public class Solution {

    public int[] singleNumbers(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num]++;
        }
        int[] res = new int[2];
        int c = 0;
        for (int num : nums) {
            if(bucket[num] == 1){
                res[c++] = num;
            }
        }
        return res;
    }
}
