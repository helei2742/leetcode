package 滑动窗口.leetcode_220_存在重复元素III;


import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //过不了
/*        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int l = Math.max(0, i-k), r = i+k;
            for (int j = l; j<=r&&j<len; j++) {
                if(j==i) continue;
                long abs = Math.abs((long) nums[i] -(long) nums[j]);
                if(abs<=t) return true;
            }
        }
        return false;*/

        int len = nums.length;

        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            if(i>k){
                treeSet.remove((long)nums[i-k-1]);
            }

            Long ceiling = treeSet.ceiling((long) nums[i] - (long) t);
            if(ceiling!=null&& ceiling<=(long) nums[i] + (long) t){
                return true;
            }
            treeSet.add((long)nums[i]);
        }
        return false;
    }
}