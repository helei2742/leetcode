package 深度优先搜索.leetcode_685_冗余连接2;

import java.util.HashSet;
import java.util.List;

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;
        UnionFind unionFind = new UnionFind(n+1);
        //记录每个点的直接父亲节点
        int[] parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int conflict = -1;
        int cycle = -1;

        for (int i = 0; i < n; i++) {
            int u = edges[i][0], v = edges[i][1];
            if(parent[v]!=v){
                conflict = i;
            }else {
                parent[v] = u;
                if(unionFind.findF(u) == unionFind.findF(v)){
                    cycle = i;
                } else {
                    unionFind.connect(u,v);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }
    }

    class UnionFind{
        private int []father;
        private int n;
        UnionFind(int n){
            this.n = n;
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x){
            if(father[x] != x) return father[x] = findF(father[x]);
            return father[x];
        }
        public boolean connect(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return false;
            father[fA] = fB;
            return true;
        }
    }
}