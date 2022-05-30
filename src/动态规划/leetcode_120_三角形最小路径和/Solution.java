package 动态规划.leetcode_120_三角形最小路径和;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
//        return dfs(triangle, 0,0);
        mem = new HashMap<>();
        return memdfs(triangle,0,0);
    }

    private int dfs(List<List<Integer>> triangle, int level, int index){
        if(level == triangle.size()-1)
            return triangle.get(level).get(index);

        List<Integer> currentLevel = triangle.get(level);
        int res = currentLevel.get(index);


        res += Math.min(dfs(triangle,level+1,index),
                dfs(triangle,level+1,index+1));

        return res;
    }

    private Map<Integer, Integer> mem;
    private int memdfs(List<List<Integer>> triangle, int level, int index) {
        if(mem.get(level*205 + index)!=null) return mem.get(level*205 + index);

        if(level == triangle.size()-1)
            return triangle.get(level).get(index);

        List<Integer> currentLevel = triangle.get(level);
        int res = currentLevel.get(index);


        res += Math.min(memdfs(triangle,level+1,index),
                memdfs(triangle,level+1,index+1));
        mem.put(level*205 + index, res);
        return res;
    }

    private int dp(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }
        int ans = dp[n-1][0];
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, dp[n-1][i]);
        }
        return ans;
    }
}
