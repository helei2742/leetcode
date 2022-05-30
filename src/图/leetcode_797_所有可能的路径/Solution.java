package 图.leetcode_797_所有可能的路径;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    private int n;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n = graph.length - 1;
        dfs(graph, 0, new ArrayList<>());
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private void dfs(int[][] graph, int current, List<Integer> path){
        if(current == n){
            List<Integer> t = new ArrayList<>(path);
            t.add(n);
            ans.add(t);
        }
        for (int next : graph[current]) {
            path.add(current);
            dfs(graph, next, path);
            path.remove(path.size()-1);
        }
    }


}