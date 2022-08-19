package 图.leetcode_1129_颜色交替的最短路径;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private final int INF = Integer.MAX_VALUE/2;
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[][] redGraph = new int[n][n];
        int[][] blueGraph = new int[n][n];

        for (int[] redEdge : redEdges) {
            redGraph[redEdge[0]][redEdge[1]] = 1;
        }
        for (int[] blueEdge : blueEdges) {
            blueGraph[blueEdge[0]][blueEdge[1]] = 1;
        }

        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0,0});
        queue.offer(new int[]{0,1});

        int d = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] head = queue.poll();
                int now = head[0], color = head[1];
                dis[now] = Math.min(dis[now], d);

                if(color == 0){
                    for (int next = 0; next < redGraph[now].length; next++) {
                        if(redGraph[now][next]==1&&!visited[next][1]){
                            visited[next][1] = true;
                            queue.offer(new int[]{next, 1});
                        }
                    }
                }else {
                    for (int next = 0; next < blueGraph[now].length; next++) {
                        if(blueGraph[now][next]==1&&!visited[next][0]){
                            visited[next][0] = true;
                            queue.offer(new int[]{next, 0});
                        }
                    }
                }
            }
            d++;
        }

        for (int i = 0; i < dis.length; i++) {
            if(dis[i] == INF) dis[i] = -1;
        }
        return dis;
    }
}
