package 剑指Offer二.leetcode_剑指OfferII106_二分图;

import java.util.Arrays;

class Solution {
    private boolean res = true;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, 0);
        for (int i = 0; i < n; i++) {
            if(color[i] == 0)
                dfs(graph, color, i, 1);
        }
        return res;
    }

    private void dfs(int[][] graph, int[] color, int now, int nowColor) {
        color[now] = nowColor;
        int nextColor = nowColor == 1?-1:1;
        for (int next : graph[now]) {
            if(color[next] == 0){
                dfs(graph, color, next, nextColor);
                if(!res){
                    return;
                }
            }else if(color[next] != nextColor){
                res = false;
                return;
            }
        }
    }
}
