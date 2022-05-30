package 动态规划.leetcode_446_等差数列划分2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;

        if(n<3) return 0;

        //dp[i].get(j) 表示在nums[] i位置公差为j的子序列有多长
        Map<Long, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = (long) nums[i] - nums[j];
                if(d>=Integer.MAX_VALUE||d<=Integer.MIN_VALUE) continue;
                dp[i].put(d,
                        dp[i].getOrDefault(d,0) +
                                dp[j].getOrDefault(d,0) +1);

                if(dp[j].containsKey(d)){
                    res += dp[j].get(d);
                }
            }
        }
        return  res;
    }

}
