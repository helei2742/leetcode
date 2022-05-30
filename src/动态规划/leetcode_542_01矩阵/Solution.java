package 动态规划.leetcode_542_01矩阵;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private static int[][] direction = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    private boolean [][] isVisited;
    private int[][] mem;
    private int row;
    private int col;
    public int[][] updateMatrix(int[][] mat) {
        row = mat.length;
        col = mat[0].length;
        mem = new int[row][col];
        isVisited = new boolean[row][col];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(mat[i][j] == 0){
                    isVisited[i][j] = true;
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int[] ints : direction) {
                int nextX = x + ints[0];
                int nextY = y + ints[1];
                if(isArea(nextX, nextY) && !isVisited[nextX][nextY]){
                    mem[nextX][nextY] = mem[x][y] + 1;
                    queue.offer(new int[]{nextX, nextY});
                    isVisited[nextX][nextY] = true;
                }
            }
        }
        return mem;
    }

    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

}
