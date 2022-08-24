package 剑指Offer二.leetcode_剑指Offer57II_和为s的连续正数序列;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] findContinuousSequence(int target) {
        int limit = (target - 1)/2;
        List<int[]> res = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            int sum = i;
            for (int j = i+1; sum<target; j++) {
                sum += j;
                if(sum==target){
                    int[] t = new int[j-i];
                    for (int k = 0; k <= j-i; k++) {
                        t[k] = k;
                    }
                    res.add(t);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public int[][] slidingWindow(int tar){
        int left = 1, right = 2;
        List<int[]> res = new ArrayList<>();

        while (left<right){
            int sum = (right+left)*(right-left+1)/2;
            if(sum == tar){
                int[] t = new int[right-left+1];
                for (int i = 0; i <= right-left; i++) {
                    t[i] = left +i;
                }
                res.add(t);
                left++;
            }else if(sum < tar){
                right++;
            }else{
                left++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
