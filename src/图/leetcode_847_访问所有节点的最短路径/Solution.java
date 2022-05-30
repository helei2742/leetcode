package 图.leetcode_847_访问所有节点的最短路径;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][1<<n];
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1<<i, 0});
            seen[i][1<<i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()){
            int[] head = queue.poll();
            int cur = head[0], mask = head[1], dis = head[2];

            if(mask == (1<<n) - 1){
                ans = dis;
                break;
            }

            for (int next : graph[cur]) {
                int nextMask = (1<<next) | mask;
                if(!seen[next][nextMask]){
                    queue.add(new int[]{next, nextMask, dis+1});
                    seen[next][nextMask] = true;
                }
            }
        }
        return ans;
    }
}