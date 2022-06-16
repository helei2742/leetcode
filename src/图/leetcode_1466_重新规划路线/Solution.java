package 图.leetcode_1466_重新规划路线;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    int[] head, edge, value,next;
    int index = 0;
    private void add(int u, int v, int w){
        index++;
        edge[index] = v;
        next[index] =  head[u];
        value[index] = w;
        head[u] = index;
    }

    public int minReorder(int n, int[][] connections) {
        int m = connections.length;
        head = new int[n];
        edge = new int[4*m];
        value = new int[4*m];
        next = new int[4*m];

        //建图，逆向边权为0，正向为1， 这样只需要从0开始bfs，计算边权则是需要规划当路线
        for (int[] connection : connections) {
            int u = connection[0], v = connection[1];
            add(u, v, 1);
            add(v, u, 0);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int res = 0;
        while (!queue.isEmpty()){
            int now = queue.poll();
            for (int i = head[now]; i != 0 ; i=next[i]) {
                int nt = edge[i];
                int w = value[i];
                if(!visited[nt]) {
                    visited[nt] = true;
                    res += w;
                    queue.add(nt);
                }
            }
        }
        return res;
    }
}