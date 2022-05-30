package 动态规划.leetcode_576_出界的路径数;

import java.util.Arrays;

public class Solution {

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {


        row = m;
        col = n;
/*
        mem = new int[m][n][maxMove+1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Arrays.fill(mem[i][j], -1);
            }
        }
        return dfs(startRow,startColumn,maxMove);*/

        return dp(m, n, maxMove, startRow, startColumn) ;
    }
    private int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    private int row;
    private int col;
/*
    private int[][][] mem;
    private int dfs(int x, int y, int k){
        if(x<0||x>=row||y>=col||y<0) return 1;
        if(k<=0) return 0;

        if(mem[x][y][k] != -1) return mem[x][y][k];
        int res = 0;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            res += dfs(nextX, nextY,k-1);
            res %= (int)1e9+7;
        }
        return mem[x][y][k] = res;
    }*/

    private int dp(int m, int n, int maxMove, int startRow, int startColumn){

        int[][] dp = new int[m*n][maxMove+1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i==0){
                    load(i,j,dp,maxMove);
                }
                if(i==m-1){
                    load(i,j,dp,maxMove);
                }
                if(j==0){
                    load(i,j,dp,maxMove);
                }
                if(j==n-1){
                    load(i,j,dp,maxMove);
                }
            }
        }

        for (int i = 1; i <= maxMove; i++) {
            for (int j = 0; j < m*n; j++) {
                int[] point = getPoint(j);
                for (int[] direction : directions) {
                    int nextX = point[0] + direction[0];
                    int nextY = point[1] + direction[1];
                    if(nextX<0||nextX>=row||nextY<0||nextY>=col) continue;
                    int nextIndex = getIndex(nextX, nextY);
                    dp[j][i] += dp[nextIndex][i-1];
                    dp[j][i] %=(int)1e9+7;
                }
            }
        }
        return dp[getIndex(startRow, startColumn)][maxMove];
    }

    private int getIndex(int x, int y) {
        return x*col+y;
    }
    private int[] getPoint(int index){
        return new int[]{index/col,index%col};
    }
    private void load(int i, int j, int[][] dp, int maxMove){
        for (int k = 1; k <= maxMove; k++) {
            dp[getIndex(i,j)][k]++;
        }
    }
}
