package 并查集.leetcode_103_被围绕的区域;

public class Solution {
    private final int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};
    private int cols;
    private int rows;
    private int getIndex(int x, int y) {
        return x * cols + y;
    }
    private boolean isInLine(int x, int y){
        return x == 0 || x == rows -1 || y ==0 || y == cols -1;
    }
    private boolean isInArea(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    public void solve(char[][] board) {
        rows = board.length;
        cols = board[0].length;
        int size = cols * rows;

        UnionFind unionFind = new UnionFind(size + 1);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == 'O'){
                    int index =  getIndex(i, j);
                    if(isInLine(i, j)){
                        unionFind.union(index, size);
                    }else {
                        for (int[] direction : DIRECTIONS) {
                            int x = i + direction[0];
                            int y = j + direction[1];
                            if(isInArea(x, y) && board[x][y] == 'O')
                                unionFind.union(index, getIndex(x, y));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == 'O' && unionFind.isUnion(getIndex(i, j), size))
                    board[i][j] = 'O';
                else
                    board[i][j] = 'X';
            }
        }
    }
    class UnionFind{
        private int[] father;
        UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x) {
            if(x != father[x]) father[x] = findF(father[x]);
            return father[x];
        }
        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father[fA] = fB;
        }
        public boolean isUnion(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            return fA == fB;
        }
    }
}
