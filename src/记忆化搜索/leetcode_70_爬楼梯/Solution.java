package 记忆化搜索.leetcode_70_爬楼梯;

public class Solution {
    public int climbStairs(int n) {
        int [] f = new int[n];
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < n; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f[n-1];
    }


    int [] mem;
    private int dfs(int n){
        if(n<=1) return 1;
        if(mem[n] != -1) return mem[n];
        return mem[n] = dfs(n-2) + dfs(n - 1);
    }
}
