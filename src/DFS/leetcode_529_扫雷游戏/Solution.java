package DFS.leetcode_529_扫雷游戏;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private int row;
    private int col;
    private int [][] DIRECTIONS = {{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
    private Set<Integer> isVisited;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return null;
        isVisited = new HashSet<>();
        row = board.length;
        col = board[0].length;

        int x = click[0], y = click[1];
        if(board[x][y] == 'M')
            board[x][y] = 'X';
        else
            dfs(board, x, y);
        return board;
    }

    private int getSetVal(int x, int y){
        return x*100000+y;
    }
    private boolean isArea(int x, int y){
        return x>=0 && x<row && y>=0 && y<col;
    }

    private char getSimple(char[][] board, int x, int y){
        int res = 0;
        for (int[] direction : DIRECTIONS) {
            int x1 = x +direction[0];
            int y1 = y + direction[1];
            if(isArea(x1, y1) && board[x1][y1]=='M') res++;
        }
        return res==0?'B': String.valueOf(res).charAt(0);
    }

    private void dfs(char[][] board, int x, int y){
        if(!isArea(x, y) || isVisited.contains(getSetVal(x,y))) return;
        isVisited.add(getSetVal(x,y));
        board[x][y] = getSimple(board, x, y);
        if(board[x][y] == 'B') {
            for (int[] direction : DIRECTIONS) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                dfs(board, nextX, nextY);
            }
        }
    }
}
