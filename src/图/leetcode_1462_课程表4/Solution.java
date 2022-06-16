package 图.leetcode_1462_课程表4;

import java.util.*;

class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {

         //floyd算法，判断两点是否可达，，i到j可达，则说明i是j的前置
//        boolean[][] floyd = new boolean[numCourses][numCourses];
//
//        for (int[] prerequisite : prerequisites) {
//            floyd[prerequisite[0]][prerequisite[1]] = true;
//        }
//
//        for (int mid = 0; mid < numCourses; mid++) {
//            for (int i = 0; i < numCourses; i++) {
//                for (int j = 0; j < numCourses; j++) {
//                    floyd[i][j] = floyd[i][j] || (floyd[i][mid]&&floyd[mid][j]);
//                }
//            }
//        }
//
//        List<Boolean> ans = new ArrayList<>();
//
//        for (int[] query : queries) {
//            ans.add(floyd[query[0]][query[1]]);
//        }
//        return ans;
        return tp(numCourses, prerequisites, queries);
    }

    private List<Boolean> tp(int numCourses, int[][] prerequisites, int[][] queries){
        List<Set<Integer>> pre = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDup = new int[numCourses];

        //拓扑，并且存储每个节点的前置，当访问到下一个节点j时，在j节点到前置中加入当前节点以及当前节点的前置，set去重
        for (int i = 0; i < numCourses; i++) {
            pre .add(new HashSet<>());
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[0], to = prerequisite[1];
            inDup[to]++;
            graph.get(from).add(to);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(inDup[i] == 0)
                queue.offer(i);
        }

        while (!queue.isEmpty()){
            int now = queue.poll();
            for (Integer next : graph.get(now)) {

                pre.get(next).addAll(pre.get(now));
                pre.get(next).add(now);
                if(--inDup[next] == 0){
                    queue.offer(next);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            if(pre.get(query[1]).contains(query[0])) ans.add(true);
            else ans.add(false);
        }
        return ans;
    }
}