package 图.leetcode_1857_有向图中最大颜色值;

import java.util.*;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        char[] chars = colors.toCharArray();

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDup = new int[n];
        int[][] dp = new int[n][26];

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            inDup[edges[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if(inDup[i] == 0) {
                queue.offer(i);
                dp[i][chars[i] - 'a'] = 1;
            }
        }

        int count = 0;
        int res = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            count++;

            for (Integer next : graph.get(now)) {
                int nextC = chars[next]-'a';
                for (int i = 0; i < 26; i++) {
                    if(nextC == i){
                        dp[next][i] = Math.max(dp[next][i], dp[now][i]+1);
                    }else {
                        dp[next][i] = Math.max(dp[next][i], dp[now][i]);
                    }
                    res = Math.max(res, dp[next][i]);
                }
                if(--inDup[next] == 0){
                    queue.add(next);
                }
            }
        }

        if (count!=n) return -1;
        return res;
    }
}