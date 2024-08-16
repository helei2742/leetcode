package 剑指Offer二.leetcode_剑指OfferII116_省份数量;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        UnionFound unionFound = new UnionFound(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(isConnected[i][j] == 1){
                    unionFound.connect(i, j);
                }
            }
        }
        
        return unionFound.count;
    }

    class UnionFound{
        private int[] father;
        private int count;
        private int[] rank;
        UnionFound(int n){
            father = new int[n];
            count = n;
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                rank[i] = 1;
            }
        }
        public int findF(int x){
            if(father[x]!=x){
                father[x] = findF(father[x]);
            }
            return father[x];
        }
        public void connect(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            count--;
            rank[fB] += rank[fA];
            father[fA] = fB;
        }
    }
}
