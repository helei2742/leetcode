package 剑指offer.leetcode_剑指Offer21调整数组顺序使奇数位于偶数前面;

import java.util.LinkedList;

public class Solution {
    public int[] exchange(int[] nums) {
        //暴力过不了
/*        LinkedList<Integer> list = new LinkedList<>();

        for (int num : nums) {
            if(num%2==1) list.addFirst(num);
            else list.addLast(num);
        }
        int [] res = new int[nums.length];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;*/

        int l = 0, r = nums.length - 1;
        while (l<r){
            while (l<r&&nums[l]%2==1){
                l++;
            }
            while (l<r&&nums[r]%2==0){
                r--;
            }
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
        }
        return nums;
    }
}
