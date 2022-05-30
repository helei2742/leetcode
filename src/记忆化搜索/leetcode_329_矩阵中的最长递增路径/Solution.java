package 记忆化搜索.leetcode_329_矩阵中的最长递增路径;

import java.util.Arrays;

public class Solution {
    private final int [][] directions = {{-1,0}, {1,0},{0,-1},{0,1}};
    //记录x,y点出发所能走的最长路径
    private int[][] mem;
    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0){
            return 0;
        }
        row = matrix.length;
        col = matrix[0].length;
        mem = new int[row][col];
        int ans = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(dfs(matrix, i, j), ans);
            }
        }
        return ans;
    }

    //返回值表示从x,y出发所能走的最长路径
    private int dfs(int [][] graph, int x, int y){
        if(mem[x][y] != 0) return mem[x][y];

        int res = 0;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(isArea(nextX, nextY) && graph[x][y]<graph[nextX][nextY]){
                res = Math.max(dfs(graph,nextX,nextY), res);
            }
        }
        return mem[x][y] = res + 1;
    }
}
