package 记忆化搜索.leetcode_509_斐波那契数列;

public class Solution {
    public int fib(int n) {
        mem = new int[n + 1];
        mem[0] = 0;
        mem[1] = 1;

        return dfs(n);
    }
    private int dp(int n){
        if(n < 2) return n;
        int r = 1,p=0,q=0;
        for(int i = 2; i<=n;i++){
            p=q;
            q=r;
            r=p+q;
        }
        return r;
    }
    private int[] mem;
    private int dfs(int n){
        if(n==1) return 1;
        if(n==0) return 0;
        if(mem[n] != 0) return mem[n];
        return mem[n] = dfs(n-1) + dfs(n+1);
    }
}
