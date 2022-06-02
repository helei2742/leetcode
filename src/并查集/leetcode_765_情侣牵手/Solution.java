package 并查集.leetcode_765_情侣牵手;

public class Solution {
    /*
           （红 黑 |  黄 红 | 黑 黄  ） （紫  蓝 | 蓝 紫）    两个联通块
                需交换2次                   需交换1次

             红红 黑黑 黄黄 紫紫  蓝蓝                        5个联通块
        交换后的连通分量个数 - 交换之前的联通分量的个数 = 需要交换的次数
               情侣个数n           并查集根数           答案
    * */
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int n = len/2;
        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < len; i+=2) {
            unionFind.union(row[i]/2, row[i+1]/2);
        }
        return n - unionFind.getCount();
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
