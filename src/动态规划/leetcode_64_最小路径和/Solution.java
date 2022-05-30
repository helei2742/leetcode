package 动态规划.leetcode_64_最小路径和;
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid==null||grid.length<1) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int [][] dp = new int[row][col];

        for (int i = 0, pre = 0; i < row; i++) {
            dp[i][0] = grid[i][0] + pre;
            pre = dp[i][0];
        }
        for (int i = 0, pre = 0; i < col; i++) {
            dp[0][i] = grid[0][i] + pre;
            pre = dp[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
}