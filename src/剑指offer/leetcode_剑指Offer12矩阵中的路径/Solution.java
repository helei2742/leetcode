package 剑指offer.leetcode_剑指Offer12矩阵中的路径;

import java.util.Arrays;

public class Solution {

    public static final int [][] DIRECTIONS = new int[][]{{1 ,0},{-1,0}, {0,1},{0,-1}};
    private boolean isArea(int x, int y){
        return x>=0&&y>=0&&x<row&&y<col;
    }
    private int row = 0;
    private int col = 0;
    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        v = new boolean[row][col];
        char[] chars = word.toCharArray();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if(dfs(board,i,j,chars,0)) return true;
            }
        }
        return false;
    }

    private boolean[][] v;
    private boolean dfs(char[][] board, int x, int y,char[] word, int index){
        if(board[x][y] != word[index]) {
            return false;
        }else if(index==word.length-1){
            return true;
        }
        v[x][y] = true;
        boolean res = false;
        for (int[] direction : DIRECTIONS) {
            int nX = direction[0] + x;
            int nY = direction[1] + y;
            if(isArea(nX,nY)&& !v[nX][nY]){
                if(dfs(board, nX, nY, word, index+1)){
                    res = true;
                    break;
                }
            }
        }
        v[x][y] = false;
        return res;
    }
}
