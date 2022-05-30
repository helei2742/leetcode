package 图.leetcode_332_重新安排行程;

import java.util.*;

class Solution {

    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private List<String> ans = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if(!graph.containsKey(from)){
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }

        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }
    private void dfs(String src){

        while(graph.containsKey(src) && graph.get(src).size()>0){
            String next = graph.get(src).poll();
            dfs(next);
        }
        ans.add(src);
    }
}