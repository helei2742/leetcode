package 并查集.leetcode_803_打砖块;

class Solution {
    public static final int[][] DIRECTIONS ={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private int row;
    private int col;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.row = grid.length;
        this.col = grid[0].length;

        //建立特殊索引， 与其相连的为稳定的砖块
        int size = row * col;

        int [][] copy = new int[row][col];

        for (int i = 0; i < row; i++) {
            if (col >= 0) System.arraycopy(grid[i], 0, copy[i], 0, col);
        }

        //被打掉的砖块为0
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }

        UnionFind unionFind = new UnionFind(size + 1);

        //屋顶（第一排）的一定是稳定的，将其与屋顶相连
        for (int j = 0; j < col; j++) {
            if (copy[0][j] == 1) {
                unionFind.union(j, size);
            }
        }

        //遍历，连结相连砖块
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(copy[i][j] == 1){
                    if (copy[i-1][j] == 1){
                        unionFind.union(getIndex(i-1, j), getIndex(i, j));
                    }
                    if(j > 0 && copy[i][j-1] == 1){
                        unionFind.union( getIndex(i, j-1), getIndex(i, j));
                    }
                }
            }
        }

        int hitsLen = hits.length;
        int[] results = new int[hitsLen];

        //从最后一个被击落的砖块向前还原，打掉的砖块数 = 修补后的砖块数-修补前的砖块数-1（一为hits中击落的不计数）
        for (int i = hitsLen - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];

            //该点本身就没砖块，不考虑
            if(grid[x][y] == 0) continue;

            //修复之前与屋顶相连的砖块
            int before = unionFind.getSize(size);

            //在第一排，直接与屋顶相连
            if(x == 0)
                unionFind.union(y, size);
            //四周砖块加入击落点的集合中，如果四周的点与屋顶相连，并查集自然就将其连到集合中，表示稳定
            for (int[] direction : DIRECTIONS) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                if(isArea(nextX, nextY) && copy[nextX][nextY] == 1) {
                    unionFind.union(getIndex(x, y), getIndex(nextX, nextY));
                }
            }

            //填补后的与屋顶相连的数量
            int after = unionFind.getSize(size);

            results[i] = Math.max(0, after - before - 1);

            //设置为1，真正的填上
            copy[x][y] = 1;
        }

        return  results;
    }

    private int getIndex(int x, int y) {
        return x * this.col + y;
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    class UnionFind {
        private final int[] father;
        private final int[] size;
        UnionFind(int size) {
            this.father = new int[size];
            this.size = new int[size];
            for(int i = 0; i < size; i++){
                this.size[i] = 1;
                this.father[i] = i;
            }
        }
        public int findF(int x) {
            if(father[x] != x) father[x] = findF(father[x]);
            return father[x];
        }
        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            this.size[fB] += this.size[fA];
            father[fA] = fB;
        }
        public int getSize(int index) {
            int f = findF(index);
            return this.size[f];
        }
    }
}