package 深度优先搜索.leetcode_695_岛屿的最大面积;

class Solution {
    private final int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

    public int maxAreaOfIsland(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }
    private int dfs(int[][] grid, int x, int y){
        if(grid[x][y] == 0) {
            return 0;
        }
        int res = 1;
        grid[x][y] = 0;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(isArea(nextX,nextY)){
                res += dfs(grid,nextX,nextY);
            }
        }
        return res;
    }
}