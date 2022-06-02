package DFS.leetcode_417_太平洋大西洋水流问题;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int col;
    private int row;
    private int[][] DIRECTIONS = {{0,1},{0,-1},{-1,0},{1,0}};
    private boolean isArea(int x, int y){
        return x>=0 && x<row && y>=0 && y<col;
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0|| heights[0].length == 0) return null;
        row = heights.length;
        col = heights[0].length;

        int [][] A = new int[row][col], B = new int[row][col];
        
        for (int i = 0; i < col; i++) {
            dfs(heights, 0, i, A);
            dfs(heights, row-1, i, B);
        }
        for (int i = 0; i < row; i++) {
            dfs(heights, i, 0, A);
            dfs(heights, i, col-1, B);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(A[i][j] == 1 && B[i][j] == 1){
                    List<Integer> t = new ArrayList<>();
                    t.add(i);
                    t.add(j);
                    ans.add(t);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] graph, int x, int y, int [][]mem) {
        if(mem[x][y] > 0) return;
        mem[x][y] = 1;

        for (int[] direction : DIRECTIONS) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (isArea(nextX, nextY) && graph[nextX][nextY] >= graph[x][y]){
                dfs(graph, nextX, nextY, mem);
            }
        }
    }
}
