package 剑指Offer二.leetcode_剑指OfferII057_值和下标之差都在给定的范围内;

import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        int right = 0;
        TreeSet<Long> treeSet = new TreeSet<>();

        while(right < len){
            if(right > k){
                treeSet.remove((long)nums[right - k - 1]);
            }

            Long ceiling = treeSet.ceiling((long) nums[right] - (long)t);
            if(ceiling != null && ceiling <= (long)nums[right] + (long)t){
                return true;
            }
            treeSet.add((long) nums[right]);
            right++;
        }
        return false;
    }
}