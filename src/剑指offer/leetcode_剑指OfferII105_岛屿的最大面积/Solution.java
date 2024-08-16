package 剑指offer.leetcode_剑指OfferII105_岛屿的最大面积;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }
    private final int[][] DIRECTIONS = {{0,-1},{0,1},{-1,0},{1,0}};
    public int maxAreaOfIsland(int[][] grid) {
        if(grid==null||(row = grid.length) == 0||(col=grid[0].length)==0){
            return 0;
        }

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == 1){
                    int bfs = bfs(grid, i, j);
                    res = Math.max(res, bfs);
                    System.out.println(bfs+"--"+i+"--"+j);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int x, int y){
        if(!isArea(x, y) || grid[x][y] == 0) return 0;
        grid[x][y] = 0;

        int res = 1;
        for (int[] direction : DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            res += dfs(grid, nx, ny);
        }
        return res;
    }

    private int bfs(int[][] grid, int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        int res = 0;
        grid[x][y] = 0;
        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            res++;
            for (int[] direction : DIRECTIONS) {
                int nx = direction[0] + poll[0];
                int ny = direction[1] + poll[1];
                if(isArea(nx, ny) && grid[nx][ny] == 1){
                    grid[nx][ny] = 0;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return res;
    }
}
