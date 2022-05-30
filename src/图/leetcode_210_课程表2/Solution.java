package 图.leetcode_210_课程表2;

import java.util.*;

public class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDog = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDog[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if(inDog[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()){
            int head = queue.poll();
            res.add(head);
            for (Integer next : graph.get(head)) {
                if(--inDog[next]==0){
                    queue.offer(next);
                }
            }
        }

        if(res.size() != numCourses){
            return new int[0];
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }


}
