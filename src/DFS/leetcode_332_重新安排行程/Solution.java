package DFS.leetcode_332_重新安排行程;

import java.util.*;

public class Solution {
    private Map<String, PriorityQueue<String>> graph;
    private List<String> ans;
    public List<String> findItinerary(List<List<String>> tickets) {
        if(tickets == null) return null;

        graph = new HashMap<>();
        ans = new ArrayList<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if(!graph.containsKey(from)){
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).add(to);
        }

        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(String position){
        while(graph.containsKey(position) && graph.get(position).size() > 0){
            String to = graph.get(position).poll();
            dfs(to);
        }
        ans.add(position);
    }

}
