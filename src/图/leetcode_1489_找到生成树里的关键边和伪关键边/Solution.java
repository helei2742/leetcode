package 图.leetcode_1489_找到生成树里的关键边和伪关键边;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];

        //保存边原来的序号
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }

        //排序后克鲁斯卡尔算法求最小生成树权值
        Arrays.sort(newEdges, (o1, o2) -> o1[2]-o2[2]);
        UnionFind unionFind = new UnionFind(n);
        int value = 0;
        for (int[] edge : newEdges) {
            if(unionFind.union(edge[0], edge[1])){
                value += edge[2];
            }
        }

        List<List<Integer>> res = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            res.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            //必要边，当不要这条边后，得不到最小生成树
            UnionFind uf = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; j++) {
                if(i!=j && uf.union(newEdges[j][0],newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(uf.count != 1 || v > value){
                res.get(0).add(newEdges[i][3]);
                continue;
            }

            //伪必要边，由于前面已经判断过是否是必要边，这里只需要使用这条边，看构造出来的是否是最小生成树
            uf = new UnionFind(n);
            v = newEdges[i][2];
            uf.union(newEdges[i][0], newEdges[i][1]);
            for (int j = 0; j < m; j++) {
                if(i!=j && uf.union(newEdges[j][0],newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(v == value){
                res.get(1).add(newEdges[i][3]);
            }
        }
        return res;
    }

    class UnionFind{
        private int[] father;
        private int[] size;
        int count;
        UnionFind(int n){
            father = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
            count = n;
        }

        public int findF(int x){
            if(father[x]!=x) father[x] = findF(father[x]);
            return father[x];
        }
        public boolean union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB){
                return false;
            }
            if(size[fA] < size[fB]){
                int t = size[fA];
                size[fA] = size[fB];
                size[fB] = t;
            }
            father[fB] = fA;
            size[fA] += size[fB];
            count--;
            return true;
        }
    }
}