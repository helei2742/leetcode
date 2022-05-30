package 图.leetcode_1042_不邻接植花;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            graph.get(path[0]-1).add(path[1]-1);
            graph.get(path[1]-1).add(path[0]-1);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] color = new boolean[5];

            for (Integer next : graph.get(i)) {
                color[ans[next]] = true;
            }
            for (int c = 1; c <= 4; c++) {
                if(!color[c]){
                    ans[i] = c;
                    break;
                }
            }
        }
        return ans;
    }
}
