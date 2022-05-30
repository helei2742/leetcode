package 图.leetcode_851_喧闹与富有;

import java.util.*;

public class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<List<Integer>> graph = new ArrayList<>();
        int n = quiet.length;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] ints : richer) {
            graph.get(ints[1]).add(ints[0]);
        }

        int [] ans = new int[n];
        Arrays.fill(ans, -1);
//        for (int i = 0; i < n; i++) {
//            ans[i] = getMostQuietOfPath(graph, quiet, i);
//        }
        for (int i = 0; i < n; i++) {
            dfs2(graph, quiet, i, ans);
        }
        return ans;
    }

    private int INF = Integer.MAX_VALUE/2;
    private void dfs(List<List<Integer>> graph, int[] quiet, int cur, int[] ans) {
        if(ans[cur] != -1){
            return;
        }
        ans[cur] = cur;
        for (Integer next: graph.get(cur)) {
            dfs(graph, quiet, next, ans);
            if(quiet[ans[next]] < quiet[ans[cur]]){
                ans[cur] = ans[next];
            }
        }
    }

    private int dfs2(List<List<Integer>> graph, int[] quiet, int cur, int[] ans){
        if(ans[cur] != -1){
            return ans[cur];
        }
        int res = cur;
        for (Integer next : graph.get(cur)) {
            int idxNext = dfs2(graph, quiet, next, ans);
            if(quiet[res] < quiet[idxNext]){
                res = idxNext;
            }
        }
        return ans[cur] = res;
    }

    //一个一个广搜超时
    private int getMostQuietOfPath(List<List<Integer>> graph, int[] quiet, int cur){
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(cur);
        int res = cur;
        int min = INF;
        while (!queue.isEmpty()){
            int now = queue.poll();
            if(now != cur && min > quiet[now]){
                min = quiet[now];
                res = now;
            }
            for (Integer next : graph.get(now)) {
                queue.offer(next);
            }
        }
        return res;
    }
}
