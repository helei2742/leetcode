package 图.leetcode_1697_检查边长度的路径是否存在;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
        int m = queries.length;

        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            index.add(i);
        }
        index.sort((o1, o2) -> queries[o1][2] - queries[o2][2]);

        UnionFind unionFind = new UnionFind(n);

        boolean[] ans = new boolean[m];

        int i = 0;
        for (int j = 0; j < m; j++) {
            int[] query = queries[index.get(j)];

            while ( i < edgeList.length && edgeList[i][2] < query[2]){
                unionFind.union(edgeList[i][0], edgeList[i][1]);
                i++;
            }
            if(unionFind.isUnion(query[0], query[1])) ans[index.get(j)] = true;
        }
        return ans;
    }

    class UnionFind{
        private int[] father;
        private int[] size;
        private int count;
        UnionFind(int n){
            father = new int[n];
            size = new int[n];
            count = n;

            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }
        public int findF(int a){
            if(father[a] != a) father[a] = findF(father[a]);
            return father[a];
        }

        public boolean union(int a, int b){
            int fA = findF(a),fB = findF(b);
            if(fA == fB) return false;
            if(size[fA] < size[fB]){
                int t = size[fA];
                size[fA] = size[fB];
                size[fB] = t;
            }
            size[fA] += size[fB];
            father[fB] = fA;
            count--;
            return true;
        }
        public boolean isUnion(int a, int b){
            int fA = findF(a),fB = findF(b);
            return fA == fB;
        }
    }
}