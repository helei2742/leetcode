package DFS.leetcode_103_被围绕的区域;

public class Solution {
    private int row;
    private int col;
    private boolean isEdge(int x, int y) {
        return x == 0 || x == row -1 || y == 0 || y == col -1;
    }
    private boolean isInArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
    public void solve(char[][] board) {
        row = board.length;
        col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(isEdge(i, j)){
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

    private void dfs(char[][] board,int x, int y) {
        if(isInArea(x, y) || board[x][y] == 'X' || board[x][y] == '#')
            return;
        board[x][y] = '#';
        dfs(board, x, y-1);
        dfs(board, x, y+1);
        dfs(board, x-1, y);
        dfs(board, x+1, y);
    }
}
