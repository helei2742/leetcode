package 图.leetcode_834_树中距离之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    private List<List<Integer>> graph;
    private int[] ans;
    private int[] dp;
    //theValueOfEdge[v]，表示以v为边的终点的这条边的贡献
    private int[] theValueOfEdge;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        graph = new ArrayList<>();
        ans = new int[n];
        dp = new int[n];
        theValueOfEdge = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(0, -1);
        System.out.println(Arrays.toString(dp));
        dfs(0,-1);
        return ans;
    }

    /**
     * 树形dp， 计算cur为根所有字节点到它的距离之和
     *  dp[p] = add(dp[child]+p到child的这条边的贡献）
     *
     * @param cur
     * @param parent
     */
    private void dfs(int cur, int parent){
        dp[cur] = 0;
        theValueOfEdge[cur] = 1;
        for (Integer next : graph.get(cur)) {
            if(next == parent) continue;
            dfs(next, cur);
            dp[cur] += dp[next] + theValueOfEdge[next];
            theValueOfEdge[cur] += theValueOfEdge[next];
        }
    }

    /**
     * 记录cur为根的答案后调整树，每次向下递归都把子节点作为根，
     *      变化后
     *          dp[parent] 需要减去子节点的贡献，dp[parent] = dp[parent] - dp[child] - child到parent的边的贡献
     *          parent到上面节点的贡献也需要减去child到parent的边的贡献
     *
     *          dp[child]作为根需要加上原来父节点的贡献，dp[child] = dp[child]+dp[parent]+parent到child的边的贡献
     *          child作为父节点到上面节点的贡献同理需要加上parent到child的贡献
     * @param cur
     * @param parent
     */
    private void dfs2(int cur, int parent){
        ans[cur] = dp[cur];
        for (Integer next : graph.get(cur)) {
            if(next == parent) continue;
            int tdp1 = dp[cur], tdp2 = dp[next], tv1 = theValueOfEdge[cur], tv2 = theValueOfEdge[next];

            dp[cur] = dp[cur] - dp[next] - theValueOfEdge[next];
            theValueOfEdge[cur] -= theValueOfEdge[next];
            dp[next] = dp[next] + dp[cur] + theValueOfEdge[cur];
            theValueOfEdge[next] += theValueOfEdge[cur];

            dfs2(next, cur);

            dp[cur] = tdp1;
            theValueOfEdge[cur] = tv1;
            dp[next] = tdp2;
            theValueOfEdge[next] = tv2;
        }
    }
}