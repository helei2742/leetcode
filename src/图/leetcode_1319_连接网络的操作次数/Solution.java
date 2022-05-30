package 图.leetcode_1319_连接网络的操作次数;

import java.util.Arrays;

public class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);

        for (int[] connection : connections) {
            unionFind.union(connection[0], connection[1]);
        }

        return unionFind.count - 1;
    }

    private class UnionFind{
        private int[] parent;
        private int[] size;
        private int count;
        private int n;
        UnionFind(int n){
            this.n = n;
            parent = new int[n];
            size = new int[n];
            count = n;
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int findParent(int x){
            if(parent[x] != x) parent[x] = findParent(parent[x]);
            return parent[x];
        }
        public boolean union(int a, int b){
            int pA = findParent(a);
            int pB = findParent(b);
            if(pA == pB) return false;
            if(size[pA] < size[pB]){
                int t = pA;
                pA = pB;
                pB = t;
            }
            size[pA] += size[pB];
            parent[pB] = pA;
            count--;
            return true;
        }
    }
}
