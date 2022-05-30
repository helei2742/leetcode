package 深度优先搜索.leetcode_743_网络延迟时间;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new List[n];
        int[] dist = new int[n];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
        for (int[] time : times) {
            int u = time[0] - 1;
            int v = time[1] - 1;
            graph[u].add(new int[]{v, time[2]});
        }
        //优先队列中，int[]数组，第一个为距离，第二个为点
        PriorityQueue<int[]> pq =
                new PriorityQueue<int[]>(
                        (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
                );
        pq.add(new int[]{0, k-1});
        dist[k-1] = 0;

        while(!pq.isEmpty()){
            int[] head = pq.poll();
            int time = head[0], now = head[1];
            for (int[] ints : graph[now]) {
                int next  = ints[0];
                int len = ints[1];
                if(dist[next] > dist[now] + len){
                    dist[next] = dist[now] + len;
                    pq.add(new int[]{len, next});
                }
            }
        }
        int ans = -1;
        for (int i : dist) {
            ans = Math.max(ans, i);
        }
        return ans==INF?-1:ans;
    }
}