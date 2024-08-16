package hot100.leetcode_56_合并区间;


import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a1, a2)->{
            return a1[0] - a2[0];
        });

        ArrayList<int[]> res = new ArrayList<>();
        int resL = -1, resR = 0;
        for (int i = 0; i < n; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if(resL == -1){
                resL = l;
                resR = r;
            }else if(l > resR) {
                res.add(new int[]{resL, resR});
                resL = l;
                resR = r;
            }else {
                resR = Math.max(resR, r);
            }
        }
        res.add(new int[]{resL,resR});
        return res.toArray(new int[0][0]);
    }
}
