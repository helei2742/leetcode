package 图.leetcode_547_省份数量;

public class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(isConnected[i][j] == 1){
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    }

    class UnionFind{
        private int[] father;
        private int count;
        UnionFind(int n){
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                count++;
            }
        }
        public int findF(int a){
            if(a != father[a]) return father[a] = findF(father[a]);
            return father[a];
        }
        public void union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            count--;
            father[fA] = fB;
        }
        public int getCount(){
            return count;
        }
    }
}
