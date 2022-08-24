package 剑指Offer二.leetcode_剑指OfferII074_合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->{
            return a[0] - b[0];
        });

        List<int[]> res = new ArrayList<>(intervals.length);
        int resL = Integer.MIN_VALUE, resR = 0;
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if(resL == Integer.MIN_VALUE || l > resR){
                if(resL != Integer.MIN_VALUE){
                    res.add(new int[]{resL, resR});
                }
                resL = l;
                resR = r;
            }else {
                resR = Math.max(r, resR);
            }
        }
        res.add(new int[]{resL, resR});
        return res.toArray(new int[res.size()][]);
    }
}
