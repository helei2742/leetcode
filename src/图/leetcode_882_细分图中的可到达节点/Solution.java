package 图.leetcode_882_细分图中的可到达节点;

import java.util.PriorityQueue;

public class Solution {
    private int[] head, next, edge, weight;
    private int index = 0;
    private int MAXN = 3005;
    private int MAXM = 100005;
    private void add(int a, int b, int w) {
        index++;
        edge[index] = b;
        weight[index] = w;
        next[index] = head[a];
        head[a] = index;
    }
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        head = new int[MAXN];
        next = new int[MAXM];
        edge = new int[MAXM];
        weight = new int[MAXM];

        int[] dis = new int[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a1,a2)->{
            return a1[1]-a2[1];
        });

        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dis[i] = Integer.MAX_VALUE/2;
            isVisited[i] = false;
        }

        for (int[] edge : edges) {
            add(edge[0],edge[1],edge[2]+1);
            add(edge[1],edge[0],edge[2]+1);
        }

        dis[0] = 0;
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()){
            int[] ints = pq.poll();
            int cur = ints[0];
            if(isVisited[cur]) continue;
            isVisited[cur] = true;
            for (int i = head[cur]; i != 0; i=next[i]) {
                int next = edge[i];
                int w = weight[i];
                if(dis[next] > dis[cur] + w){
                    dis[next] = dis[cur] + w;
                    if(!isVisited[next]){
                        pq.add(new int[]{next, dis[next]});
                    }
                }
            }
        }
        int res = 0;
        for (int[] ints : edges) {
            int a = Math.max(0, maxMoves - dis[ints[0]]);
            int b = Math.max(0, maxMoves - dis[ints[1]]);

            res += Math.min(ints[2], a+b);
        }
        for (int i = 0; i < n; i++) {
            if(dis[i] <= maxMoves) res++;
        }
        return res;
    }

}
