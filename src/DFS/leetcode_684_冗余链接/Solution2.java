package DFS.leetcode_684_冗余链接;

public class Solution2 {

    public int[] findRedundantConnection(int[][] edges) {

        UnionFind unionFind = new UnionFind(1005);

        int len = edges.length;
        for (int i = 0; i < len; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            boolean f = unionFind.union(u,v);
            if(!f){
                return edges[i];
            }
        }
        return new int[0];
    }

    class UnionFind{
        private int[] father;
        UnionFind(int n){
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x){
            if(father[x] != x) father[x] = findF(father[x]);
            return father[x];
        }
        public boolean union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA!=fB){
                father[fA] = fB;
                return true;
            }
            return false;
        }
    }
}
