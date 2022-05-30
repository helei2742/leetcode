package 动态规划.leetcode_435_无重叠区间;

import java.util.Arrays;
import java.util.Map;

public class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {

/*        int n = intervals.length;

        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int [] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(intervals[i][0] >= intervals[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return n - res;*/
        return tanxing(intervals);
    }

    //贪心，按区间右端点升序，
    private int tanxing(int[][] intervals) {

        int n = intervals.length;

        Arrays.sort(intervals, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        int ans = 1;
        int right = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if(intervals[i][0] >= right){
                ans++;
                right = intervals[i][1];
            }
        }
        return n-ans;
    }
}
