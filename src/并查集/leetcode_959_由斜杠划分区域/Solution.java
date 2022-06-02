package 并查集.leetcode_959_由斜杠划分区域;

public class Solution {
    /*
       由grid的长度，可分为许多的小方块

        将每个小方块分为
        |  0  |
        |3   1|
        |  2  | 这样的区域，当为 / 就将 0、3 连在一起  1 、2 连在一起
                              \  将 0 、1 连在一起  3、2 连在一起
                             空格  将所有的连在一起

                         小方块联通后，还要将大方块联通，
                         左下、右下，右上、右下等都可以
    * */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int size = n * n * 4;

        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < n; i++) {
            char[] rows = grid[i].toCharArray();
            for (int j = 0; j < n; j++) {
                int index = 4*(i*n + j);
                if(rows[j]=='/') {
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                }
                else if(rows[j]=='\\') {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                }
                else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }

                if(j+1 < n) {
                    unionFind.union(index + 1, 4*(i*n + j + 1) + 3);
                }
                if(i+1 < n) {
                    unionFind.union(index + 2, 4*((i+1)*n + j));
                }
            }
        }

        return unionFind.getCount();
    }
    class UnionFind {
        private int[] father;
        private int count;
        UnionFind(int n) {
            father = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                father[i] = i;
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
            father[fA] = fB;
            count--;
        }
        public int getCount() {
            return this.count;
        }
    }
}
