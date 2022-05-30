package 图.leetcode_802_找到最终的安全状态;

import java.nio.ByteBuffer;
import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        /*int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;*/
      
        return topu(graph);
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    private List<Integer> topu(int[][] graph){
        List<List<Integer>> reserveGraph = new ArrayList<>();
        int n = graph.length;

        for (int i = 0; i < n; i++) {
            reserveGraph.add(new ArrayList<>());
        }
        int[] inDu = new int[n];
        for (int from = 0; from < n; from++) {
            for (int to : graph[from]) {
                reserveGraph.get(to).add(from);
            }
            inDu[from] = graph[from].length;
        }

        Queue<Integer> queue = new LinkedList<>();


        for (int i = 0; i < n; i++) {
            if(inDu[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int head = queue.poll();
            List<Integer> edge = reserveGraph.get(head);
            for (Integer next : edge) {
                if(--inDu[next]==0){
                    queue.add(next);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(inDu[i]==0) ans.add(i);
        }
        return ans;
    }
}
