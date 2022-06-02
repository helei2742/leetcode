package 图.leetcode_1334_阀值内邻居最少的城市;

import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<int[]>> graph = new ArrayList<>();
        this.n = n;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }



        int ans = 0;
        int min = Integer.MAX_VALUE/2;
        for (int i = 0; i < n; i++) {
            int count = dijikesitra(graph, distanceThreshold, i);

            if(count < min) {
                min = count;
                ans = i;
            } else if (count == min) {
                ans = i;
            }
        }
        return ans;
    }

    private int n = 0;
    private static final int INF = Integer.MAX_VALUE/2;
    private int dijikesitra(List<List<int[]>> graph, int limit, int start){
        int[] dis = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dis, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[]{start, 0});
        dis[start] = 0;

        while(!pq.isEmpty()){
            int[] head = pq.poll();
            int now = head[0];
            if(visited[now]) continue;
            visited[now] = true;
            for (int[] ints : graph.get(now)) {
                int next = ints[0];
                int v = ints[1];
                if(dis[now] + v < dis[next]){
                    dis[next] = dis[now] + v;
                    pq.offer(new int[]{next, dis[next]});
                }
            }
        }

        int res = 0;

        for (int i = 0; i < n; i++) {
            if(dis[i] <= limit) res++;
        }
        return res;
    }
}