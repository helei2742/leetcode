package DFS.leetcode_419_甲板上的战列舰;

public class Solution {
    private int row;
    private int col;
    private int ans;
    private boolean isArea(int x, int y){
        return x>=0 && x< row && y>=0 && y<col;
    }

    public int countBattleships(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return 0;

        row = board.length;
        col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == 'X'){
                    if(isArea(i, j+1) && board[i][j+1]=='X'){
                        dfs(board, i, j,1, true);
                    }else {
                        dfs(board, i, j,1, false);
                    }
                }
            }
        }
//        dfs(board,4,4,1,false);
        return ans;
    }

    /**
     * flag 为真代表向右搜索
     * @param board
     * @param x
     * @param y
     * @param flag
     */
    private void dfs(char[][] board, int x, int y, int len, boolean flag) {
        if(!isArea(x,y) || board[x][y] == '.') {
            ans++;
            System.out.println(len);
            return;
        }
        board[x][y] = '.';

        if(flag){
            dfs(board, x, y+1, len+1, true);
        }else {
            dfs(board, x+1, y, len+1, false);
        }
    }
}
