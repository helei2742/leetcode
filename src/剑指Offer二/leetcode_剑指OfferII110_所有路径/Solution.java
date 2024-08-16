package 剑指Offer二.leetcode_剑指OfferII110_所有路径;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if(graph==null||(n=graph.length) == 0){
            return new ArrayList<>();
        }
        t = new ArrayList<>();
        res = new ArrayList<>();
        dfs(graph, 0);
        return res;
    }
    private int n;
    private List<Integer> t;
    private List<List<Integer>> res;
    private void dfs(int[][] graph, int cur) {
        if(cur == n-1){
            ArrayList<Integer> list = new ArrayList<>(t);
            list.add(cur);
            res.add(list);
            return;
        }
        for (int next : graph[cur]) {
            t.add(cur);
            dfs(graph, next);
            t.remove(t.size()-1);
        }
    }
}
