package 图.leetcode_1786_从第一个节点出发到最后一个节点的受限路径数;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private int[] head, next, weight, edge;
    private int index = 0;
    private final long INF = Long.MAX_VALUE/2;
    private final int MOD = 1000000007;

    private void add(int u, int v, int w){
        index++;
        edge[index] = v;
        weight[index] = w;
        next[index] = head[u];
        head[u] = index;
    }

    public int countRestrictedPaths(int n, int[][] edges) {
        int m = edges.length;
        head = new int[n+1];
        next = new int[4*m];
        weight = new int[4*m];
        edge = new int[4*m];

        for (int[] ints : edges) {
            add(ints[0], ints[1], ints[2]);
            add(ints[1], ints[0], ints[2]);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> (int) (o1[1] - o2[1]));
        long[] dis = new long[n + 1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dis, INF);

        pq.add(new long[]{n, 0});
        dis[n] = 0;

        while(!pq.isEmpty()){
            long[] top = pq.poll();
            int now = (int) top[0];
            if(visited[now]) continue;
            visited[now] = true;
            for (int i = head[now]; i != 0; i=next[i]) {
                int nextNode = edge[i];
                int w = weight[i];
                if(dis[nextNode] > dis[now] + w){
                    dis[nextNode] = dis[now] + w;
                    pq.offer(new long[]{nextNode, dis[nextNode]});
                }
            }
        }

        System.out.println(Arrays.toString(dis));

        mem = new long[n + 1];
        Arrays.fill(mem, -1);

        long dfs =  dfs(1, n, dis);
        System.out.println(dfs);
        return (int) dfs;
    }

    private long[] mem;
    private long dfs(int current ,int target, long[] dis){
        if(current == target) return 1;

        if(mem[current] != -1) return mem[current];

        long res = 0;
        for (int i = head[current]; i != 0; i=next[i]) {
            int nextNode = edge[i];
            if(dis[nextNode] < dis[current]){
                res += dfs(nextNode, target, dis);
                res %= MOD;
            }
        }
        return mem[current] = res;
    }
}