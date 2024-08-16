package hot100.leetcode_200_岛屿数量;

class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || (row=grid.length) == 0 || (col=grid[0].length) == 0)return 0;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1'){
                    dfs(grid,i,j);
                    res++;
                }
            }
        }
        return res;
    }
    private int row;
    private int col;
    private void dfs(char[][] grid, int x, int y){
        if(x<0 || x>=row || y<0 || y>=col || grid[x][y] != '1'){
            return;
        }
        grid[x][y] = '#';
        dfs(grid, x+1, y);
        dfs(grid, x-1, y);
        dfs(grid, x, y+1);
        dfs(grid, x, y-1);
    }
}
