package 剑指Offer二.leetcode_剑指OfferII112_最长递增路径;

import java.util.Arrays;

class Solution {
    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }
    private final int[][] DIRECTIONS = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || (row=matrix.length) == 0 || (col=matrix[0].length) == 0){
            return 0;
        }
        int res = 0;
        mem = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(mem[i], -1);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res = Math.max(dfs(matrix, i, j), res);
            }
        }
        return res;
    }
    private int[][] mem;
    private int dfs(int[][] matrix, int x, int y) {
        if(mem[x][y] != -1) return mem[x][y];

        int res = 0;
        for (int[] direction : DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if(isArea(nx, ny) && matrix[nx][ny] > matrix[x][y]) {
                res = Math.max(dfs(matrix, nx, ny), res);
            }
        }
        return mem[x][y] = res+1;
    }
}
