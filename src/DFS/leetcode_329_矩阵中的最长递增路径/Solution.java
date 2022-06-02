package DFS.leetcode_329_矩阵中的最长递增路径;

public class Solution {
    private final int[][] DIRECTIONS = {{-1,0}, {1,0}, {0,-1},{0,1}};
    private int [][] mem;
    private int row = 0;
    private int col = 0;
    private int ans = Integer.MIN_VALUE;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0 ){
            return 0;
        }
        row = matrix.length;
        col = matrix[0].length;

        mem = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
/*                if(mem[i][j] == 0) {
                    dfs(matrix, i, j);
                }*/
                dfs2(matrix, i,j, 0);
            }
        }
        return ans;
    }
    private int dfs(int[][] matrix, int x, int y){
        if(mem[x][y] > 0) {
            return mem[x][y];
        }
        mem[x][y] = 1;
        int temp = Integer.MIN_VALUE;
        for (int[] direction : DIRECTIONS) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(isArea(nextX, nextY) && matrix[x][y] < matrix[nextX][nextY]) {
                int t = dfs(matrix, nextX, nextY);
                temp = Math.max(temp, t);
            }
        }
        mem[x][y] = Math.max(mem[x][y], temp + 1);
        ans = Math.max(ans, mem[x][y]);
        return mem[x][y];
    }

    private void dfs2(int[][] matrix, int x, int y, int l){
        if(!isArea(x, y)) return;
        ans = Math.max(ans, l);
        for (int[] direction : DIRECTIONS) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(isArea(x,y) && matrix[x][y] < matrix[nextX][nextY]){
                dfs2(matrix, nextX, nextY, l + 1);
            }
        }
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }
}
