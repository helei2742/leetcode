package hot100.leetcode_96_不同的二叉搜索树;

import java.util.Arrays;

class Solution {
    public int numTrees(int n) {
        mem = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(mem[i], -1);
        }
        return dfs(1, n);
    }
    private int[][] mem;
    private int dfs(int start, int end) {
        if(start > end) return 1;
        if(mem[start][end] != -1) return mem[start][end];
        int res = 0;
        for (int i = start; i <= end; i++) {
            int left = dfs(start, i - 1);
            int right = dfs(i+1, end);
            res += left*right;
        }
        return mem[start][end] = res;
    }
}
