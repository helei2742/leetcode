package 并查集.leetcode_200_岛屿数量;

public class Solution {
    private final int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};
    private int rows;
    private int cols;
    public int numIslands(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int size = cols * rows;

        int count = 0;
        for (char[] chars : grid) {
            for (char aChar : chars) {
                if(aChar == '1') count++;
            }
        }

        UnionFind unionFind = new UnionFind(size + 1, count);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                if(grid[i][j] == '1'){
                    grid[i][j] = '0';
                    for (int[] direction : DIRECTIONS) {
                        int nextX = i + direction[0];
                        int nextY = j + direction[1];
                        if(isArea(nextX, nextY) && grid[nextX][nextY] == '1'){
                            unionFind.union(index, nextX * cols + nextY);
                        }
                    }
                }
            }
        }

        return unionFind.getCount();
    }
    private boolean isArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    class UnionFind {
        private final int[] father;
        private int count;
        UnionFind(int n, int count) {
            this.count = count;
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
            count--;
        }
        public int getCount() {
            return count;
        }
    }
}
