package 剑指offer.leetcode_剑指Offer39_数组中出现次数超过一半的数字;

public class Solution {
    public int majorityElement(int[] nums) {
        int res = 0, count = 0;
        for (int num : nums) {

            if(count == 0){
                res = num;
            }

            count += (num==res)?1:-1;
        }
        return res;
    }

}
