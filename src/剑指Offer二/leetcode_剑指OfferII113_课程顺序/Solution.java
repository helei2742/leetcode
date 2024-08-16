package 剑指Offer二.leetcode_剑指OfferII113_课程顺序;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDup = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            inDup[to]++;
            graph.get(from).add(to);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(inDup[i] == 0) {
                queue.add(i);
            }
        }
        int[] res = new int[numCourses];
        int idx = 0;
        while(!queue.isEmpty()){
            Integer now = queue.poll();
            res[idx++] = now;
            for (Integer next : graph.get(now)) {
                if(--inDup[next] == 0){
                    queue.add(next);
                }
            }
        }
        if(idx != numCourses){
            return new int[0];
        }
        return res;
    }
}
