package 深度优先搜索.leetcode_785_判断二分图;

import java.util.Arrays;

public class Solution {
    private int [] color;

    private boolean f = true;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, 0);

        for (int i = 0; i < n; i++) {
            if(color[i] == 0){
                dfs(graph, i, 1);
            }
        }
        return f;
    }

    private void dfs(int[][] graph, int now,int crtColor){
        color[now] = crtColor;
        int nextColor =(crtColor == 1?-1:1);
        for (int next : graph[now]) {
            if(color[next] == 0){
                dfs(graph, next, nextColor);
            }else {
                if(color[next] != nextColor){
                    f = false;
                    return;
                }
            }
        }
    }
}
