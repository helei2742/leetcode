package 剑指Offer二.leetcode_剑指OfferII099_最小路径之和;

import java.util.Arrays;

public class Solution {
    private int row;
    private int col;
    public int minPathSum(int[][] grid) {
        this.row = 0;
        this.col = 0;
        if(grid == null || (this.row = grid.length) == 0 || (this.col = grid[0].length) == 0){
            return 0;
        }
        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
            }
        }
        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(dp[i]));

        }
        return dp[row-1][col-1];
    }

    private int dfs(int[][] grid, int x, int y) {
        if(x == 0){
            int res = 0;
            for (int i = 0; i <= y; i++) {
                res += grid[0][i];
            }
            return res;
        }
        if(y == 0){
            int res = 0;
            for (int i = 0; i <= x; i++) {
                res += grid[i][0];
            }
            return res;
        }
        int up = dfs(grid, x-1, y);
        int left = dfs(grid, x, y-1);
        return Math.min(up, left) + grid[x][y];
    }
}
