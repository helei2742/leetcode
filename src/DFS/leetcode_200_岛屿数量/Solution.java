package DFS.leetcode_200_岛屿数量;

class Solution {
    private int col;
    private int row;
    private final int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};


    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;

        int numIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        return numIslands;
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
    private void dfs(char[][] grid, int x, int y) {
        if(!isArea(x, y) || grid[x][y] == '0') return;

        grid[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            dfs(grid, x + DIRECTIONS[i][0], y + DIRECTIONS[i][1]);
        }
    }
}