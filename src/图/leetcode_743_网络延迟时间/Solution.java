package 图.leetcode_743_网络延迟时间;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    private int[] head, next, edge, weight;
    private int index = 0;
    private int MAXN = 6005;

    private void add(int a, int b, int w) {
        index++;
        edge[index] = b;
        weight[index] = w;
        next[index] = head[a];
        head[a] = index;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        head = new int[MAXN];
        next = new int[2*MAXN];
        edge = new int[2*MAXN];
        weight = new int[2*MAXN];

        int[] dtn = new int[n+1];
        boolean[] isVisited = new boolean[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            dtn[i] = Integer.MAX_VALUE/2;
            isVisited[i] = false;
        }

        for (int[] time : times) {
            add(time[0]-1, time[1]-1, time[2]);
        }

        dtn[k-1] = 0;
        pq.add(new int[]{0, k-1});

        while(!pq.isEmpty()){
            int[] ints = pq.poll();
            int now = ints[1];

            if(isVisited[now]) continue;
            isVisited[now] = true;

            for (int i = head[now]; i != 0; i=next[i]) {
                int next = edge[i];
                int v = weight[i];

                if(dtn[now] + v < dtn[next]){
                    dtn[next] = dtn[now] + v;
                    if(!isVisited[next]){
                        pq.add(new int[]{dtn[next], next});
                    }
                }
            }
        }

        int ans = -1;

        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, dtn[i]);
        }
        return ans == Integer.MAX_VALUE/2?-1:ans;
    }
}
