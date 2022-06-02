package 图.leetcode_1377_t秒后青蛙的位置;

import java.util.*;

class Solution {
    private class Node{
        public int index;
        public int times;
        public double p;
        Node(int index, int times, double p){
            this.index = index;
            this.times = times;
            this.p = p;
        }
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]-1).add(edge[1]-1);
            graph.get(edge[1]-1).add(edge[0]-1);
        }

        boolean[] visited = new boolean[n];
        Queue<Node> queue = new LinkedList<>();


        queue.add(new Node(0, 0, 1));

        visited[0] = true;
        int timesCount = 0;

        while (!queue.isEmpty()){
            timesCount++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node head = queue.poll();
                int now = head.index;
                if(t == timesCount-1 && target -1 == now) return head.p;

                int canGoCount = getCanGOCount(now, visited, graph);
                if(canGoCount == -1) {
                    queue.add(head);
                }else {
                    double nextP = head.p /canGoCount;
                    for (Integer next : graph.get(now)) {
                        if(!visited[next]){
                            visited[next] = true;
                            queue.add(new Node(next, timesCount, nextP));
                        }
                    }
                }
            }
        }

        return 0;
    }
    private int getCanGOCount(int cur, boolean[] visited,List<List<Integer>> graph){
        int res = 0;
        for (Integer next : graph.get(cur)) {
            if(!visited[next]) res++;
        }
        return  res == 0? -1 : res;
    }
}
