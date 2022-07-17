package 剑指offer.leetcode_剑指Offer61扑克牌中的顺子;

import java.util.Arrays;

public class Solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;
        for (int i = 0; i < 4; i++) {
            if(nums[i]==0) joker++;
            else if(nums[i] == nums[i+1]) return false;
        }
        return nums[4] - nums[joker] <= 5;
    }
}
