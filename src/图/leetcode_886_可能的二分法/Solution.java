package 图.leetcode_886_可能的二分法;

import java.util.*;

class Solution {
    private int [] head, edge, next;
    private int index = 0;
    private final int MAXN = 100005;
    private void add(int from, int to){
        index++;
        edge[index] = to;
        next[index] = head[from];
        head[from] = index;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        head = new int[MAXN];
        edge = new int[MAXN];
        next = new int[MAXN];

        for (int[] dislike : dislikes) {
            add(dislike[0],dislike[1]);
            add(dislike[1], dislike[0]);
        }

//        for (int i = 1; i <= n; i++) {
//            if(!colorMap.containsKey(i)&&!dfs(i, 0)) return false;
//        }

        visited = new boolean[n+1];
        color = new boolean[n+1];
        Arrays.fill(visited, false);
        Arrays.fill(color, false);

        for (int i = 1; i <= n; i++) {
            if(!visited[i]){
                if(!bfs(i, n)) return false;
            }
        }

        return true;
    }
//    private Map<Integer, Integer> colorMap = new HashMap<>();
//
//    private boolean dfs(int node, int color){
//        if(colorMap.containsKey(node)) return colorMap.get(node) == color;
//        colorMap.put(node, color);
//        for (int i = head[node]; i != 0; i=next[i]) {
//            int next = edge[i];
//            if(!dfs(next, color^1)) return false;
//        }
//        return true;
//    }

    boolean[] visited;
    boolean[] color;
    private boolean bfs(int node, int n){
        Queue<Integer> queue = new LinkedList<>();

        Arrays.fill(visited, false);
        queue.offer(node);
        visited[node] = true;

        while (!queue.isEmpty()){
            int now = queue.poll();
            for (int i = head[now]; i != 0; i=next[i]) {
                int next = edge[i];
                if(!visited[next]){
                    visited[next] = true;
                    color[next] = !color[now];
                    queue.offer(next);
                }else{
                    if(color[next] == color[now]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}