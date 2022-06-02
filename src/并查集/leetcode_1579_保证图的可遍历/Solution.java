package 并查集.leetcode_1579_保证图的可遍历;

public class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {

        UnionFind ufA = new UnionFind(n+1);
        UnionFind ufB = new UnionFind(n+1);

        int ans = 0;
        for (int[] edge : edges) {
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];
            if(type == 3) {
                if(!ufA.union(u, v))
                    ans++;
                else
                    ufB.union(u, v);
            }
        }

        for (int[] edge : edges) {
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];
            if(type == 2) {
                if(!ufA.union(u, v)) ans++;
            }
            if(type == 1) {
                if(!ufB.union(u, v)) ans++;
            }
        }
        if(ufA.getCount() != 1 || ufB.getCount() != 1) return -1;

        return ans;
    }
    class UnionFind {
        private int[] father;
        private int count;

        UnionFind(int n) {
            count = n-1;
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x) {
            if(x != father[x]) father[x] = findF(father[x]);
            return father[x];
        }
        public boolean union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return false;
            father[fA] = fB;
            count--;
            return true;
        }
        public int getCount() {
            return this.count;
        }
    }
}
