package 剑指Offer二.leetcode_剑指OfferII107_矩阵中的距离;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int row;
    private int col;
    private final int[][] DIRECTIONS = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }
    public int[][] updateMatrix(int[][] mat) {
        if(mat == null || (row=mat.length) == 0 || (col=mat[0].length) == 0){
            return new int[0][0];
        }
        int[][] res = new int[row][col];
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(mat[i][j]==0){
                    queue.add(i * col + j);
                    isVisited[i][j] = true;
                }
            }
        }
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            int x = poll/col, y = poll%col;
            for (int[] direction : DIRECTIONS) {
                int nx = direction[0] + x;
                int ny = direction[1] + y;
                if(isArea(nx, ny) && !isVisited[nx][ny]){
                    res[nx][ny] = res[x][y] + 1;
                    queue.add(nx * col + ny);
                    isVisited[nx][ny] = true;
                }
            }
        }
        return res;
    }

}
