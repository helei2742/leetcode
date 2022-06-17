package 图.leetcode_1514_概率最大的路径;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private int[] head, edge, next;
    private double[] weight;
    private int index = 0;
    private void add(int u, int v, double w){
        index++;
        edge[index] = v;
        weight[index] = w;
        next[index] = head[u];
        head[u] = index;
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        int m = edges.length;
        head = new int[n];
        edge = new int[4*m];
        weight = new double[4*m];
        next = new int[4*m];

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double w = succProb[i];
            add(u,v,w);
            add(v,u,w);
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>((p1,p2)-> (int) (p1[1]-p2[1]>0?-1:1));
        double[] dis = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dis, 1);

        pq.add(new double[]{start, 1.0});

        while (!pq.isEmpty()){
            double[] top = pq.poll();
            int now = (int) top[0];

            if(visited[now]) continue;
            visited[now] = true;

            for (int i = head[now]; i != 0 ; i=next[i]) {
                int nextNode = edge[i];
                double w = weight[i];
                if(dis[nextNode] == 1 || dis[nextNode] < dis[now] * w){
                    dis[nextNode] = dis[now] * w;
                    pq.add(new double[]{nextNode, dis[nextNode]});
                }
            }
        }

        return dis[end]==1?0:dis[end];
    }
}