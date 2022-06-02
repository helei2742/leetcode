package DFS.leetcode_684_冗余链接;

import java.util.HashSet;
import java.util.Set;

public class  Solution {
    public int[] findRedundantConnection(int[][] edges) {

        if(edges == null || edges.length == 0 || edges[0].length == 0){
            return new int[0];
        }
        int len = edges.length;

        Set<Integer>[] map = new HashSet[len + 1];
        for(int[] edge: edges){
            if(map[edge[0]] == null){
                map[edge[0]] = new HashSet<>();
            }
            map[edge[0]].add(edge[1]);

            if(map[edge[1]] == null){
                map[edge[1]] = new HashSet<>();
            }
            map[edge[1]].add(edge[0]);
        }

        for(int i = len - 1; i >= 0; i--){
            int from = edges[i][0];
            int to = edges[i][1];
            map[from].remove(to);
            map[to].remove(from);
            boolean[] used = new boolean[len + 1];
            if(dfs(map, from, to, used)){
                if(to < from){
                    return new int[]{to, from};
                }else{
                    return new int[]{from, to};
                }
            }
            map[from].add(to);
            map[to].add(from);
        }
        return new int[0];
    }

    /*
    判断从x出发是否能到y
     */
    private boolean dfs(Set<Integer>[] map, int s, int t, boolean[] used) {
        if (s == t) {
            return true;
        }
        used[s] = true;
        for (int i : map[s]) {
            if (!used[i]) {
                if(dfs(map,i,t,used)){
                    return true;
                }
            }
        }
        return false;
    }
}
