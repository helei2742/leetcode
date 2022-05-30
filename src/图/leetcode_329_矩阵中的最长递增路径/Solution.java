package 图.leetcode_329_矩阵中的最长递增路径;

import java.util.Arrays;

public class Solution {
    private int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0 && x<row && y>=0 && y<col;
    }
    private int[][] mem;
    private int dfs(int[][] graph, int x, int y){
        if(mem[x][y] != -1) return mem[x][y];
        int res = 0;

        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(isArea(nextX,nextY) && graph[x][y] < graph[nextX][nextY]){
                res = Math.max(dfs(graph, nextX, nextY), res);
            }
        }
        return mem[x][y] = res+1;
    }

    public int longestIncreasingPath(int[][] matrix) {
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.mem = new int[row][col];

        for (int i = 0; i < row; i++) {
            Arrays.fill(mem[i], -1);
        }

        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(dfs(matrix, i, j), ans);
            }
        }
        return ans;
    }

}
