package 并查集.leetcode_103_被围绕的区域;

public class Solution_DFS {
    private int row;
    private int col;
    public void solve(char[][] board) {
        row = board.length;
        col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(isEdge(i, j)){
                    //对边上的为 O的点进行搜索标记， 表示为不能去掉的
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }
    private void dfs(char[][] board, int x, int y) {
        if(isArea(x, y) || board[x][y] == 'X' || board[x][y] == '#')
            return;
        board[x][y] = '#';
        dfs(board, x - 1, y);
        dfs(board, x, y -1);
        dfs(board, x + 1, y);
        dfs(board, x, y + 1);
    }
    private boolean isArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
    private boolean isEdge(int x, int y) {
        return x == 0 || y == 0 || x == row -1 || y == col -1;
    }
}
