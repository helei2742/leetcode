package 图.leetcode_787_k站中转内最便宜的航班;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {

    private int[][] graph;
    private int[][] mem;
    private final int INF = Integer.MAX_VALUE/2;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
/*        graph = new int[n][n];
        mem = new int[n][k+2];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], -1);
        }
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        int ans = dfs(src, dst, k+1);

        return ans >= INF?-1:ans;*/

        return dp(n, flights, src, dst, k);
    }

    private int dfs(int src, int dst, int k){
        if(k < 0) return INF;
        if(src == dst) return 0;
        if(mem[src][k] != 0) return mem[src][k];

        int[] arr = graph[src];
        int res = INF;
        for (int next = 0; next < arr.length; next++) {
            if(arr[next] != -1){
                int nextCost = dfs(next, dst, k-1);
                res = Math.min(res, nextCost + arr[next]);
            }
        }
        return mem[src][k] = res;
    }

    private int dp(int n, int[][] flights, int src, int dst, int K){
        //dp[i][k]表示从i出发到dst经过k条边需要的最小花费
        //dp[i][k] = min(dp[j][k-1]+cost[i][j])
        int[][] dp = new int[n][K+2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[dst][0]=0;
        for (int k = 1; k < K+2; k++) {
            for (int[] flight : flights) {
                dp[flight[0]][k] = Math.min(dp[flight[1]][k-1]+flight[2]
                        ,dp[flight[0]][k]);
            }
        }
        int ans = IntStream.of(dp[src]).min().getAsInt();
        return ans >= INF?-1:ans;
    }
}
