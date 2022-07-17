package 图.leetcode_1791_找出星型图的中心点;

class Solution {
    public int findCenter(int[][] edges) {
/*        int n = edges.length+1;

        int[] degree = new int[n+1];
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if(degree[i] == n-1) return i;
        }
        return -1;*/
        return fun(edges);
    }

    public int fun(int[][] edges){
        int a = edges[0][0], b = edges[0][1];
        if(a == edges[1][0] || a == edges[1][1]) return a;
        return b;
    }
}

