package 剑指Offer二.leetcode_剑指OfferII118_多余的边;

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFound unionFound = new UnionFound(n);

        for (int[] edge : edges) {
            if(unionFound.isUnion(edge[0], edge[1])){
                unionFound.union(edge[0], edge[1]);
            }else {
                return edge;
            }
        }
        return new int[0];
    }

    class UnionFound{
        private int[] father;
        UnionFound(int n){
            father = new int[n+1];
            for (int i = 0; i <= n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x) {
            if(father[x] != x) return father[x] = findF(father[x]);
            return father[x];
        }
        public void union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father[fA] = fB;
        }
        public boolean isUnion(int a, int b){
            int fA = findF(a);
            int fB = findF(b);

            return fA != fB;
        }
    }
}
