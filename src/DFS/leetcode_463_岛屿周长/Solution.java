package DFS.leetcode_463_岛屿周长;

import java.util.Arrays;

public class Solution {
    private int row;
    private int col;
    private int[][] DIRECTIONS = {{0,1},{0,-1},{1,0},{-1,0}};
    private boolean[][] isVisited;

    private int ans;
    private boolean isArea(int x, int y) {
        return x>=0 && x< row && y>=0 && y<col;
    }
    private boolean isEdge(int x, int y) {
        return (x==-1 || y==-1 || x==row || y==col);
    }
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        row = grid.length;
        col = grid[0].length;
        isVisited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == 1 && !isVisited[i][j]){
                    dfs(grid,i,j);
                    break;
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int x, int y) {
        if(!isArea(x, y) || isVisited[x][y]) return;
        isVisited[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int nextX = x+direction[0];
            int nextY = y+direction[1];
            if ((isArea(nextX,nextY)&&grid[nextX][nextY] == 0)|| isEdge(nextX,nextY)){
                ans++;
            }
            if(isArea(nextX,nextY)&&!isVisited[nextX][nextY] && grid[nextX][nextY] == 1){
                dfs(grid, nextX, nextY);
            }
        }
    }
}
