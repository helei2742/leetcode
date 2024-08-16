package hot100.leetcode_79_单词搜索;
class Solution {
    private int row;
    private int col;
    private int len;
    private String word;
    public boolean exist(char[][] board, String word) {
        if(board == null || (row=board.length)==0 || (col=board[0].length)==0){
            return false;
        }
        if((len = word.length()) == 0) return true;
        this.word = word;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(dfs(board, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int x, int y, int idx) {
        if(x<0||x>=row||y<0||y>=col||board[x][y]=='\n'||board[x][y] != word.charAt(idx)) {
            return false;
        }
        if(idx == len-1) return true;
        board[x][y] = '\n';
        boolean res =   dfs(board, x+1, y, idx+1) ||
                dfs(board, x, y+1, idx+1) ||
                dfs(board, x-1, y, idx+1) ||
                dfs(board, x, y-1, idx+1);

        board[x][y] = word.charAt(idx);
        return res;
    }

}
