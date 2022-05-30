package 图.leetcode_207_课程表;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDog = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
            inDog[prerequisite[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if(inDog[i] == 0) queue.add(i);
        }
        int count = 0;
        while (!queue.isEmpty()){
            int head = queue.poll();
            count++;
            for (Integer next : graph.get(head)) {
                if(--inDog[next] == 0){
                    queue.offer(next);
                }
            }
        }
        return count == numCourses;
    }
}
