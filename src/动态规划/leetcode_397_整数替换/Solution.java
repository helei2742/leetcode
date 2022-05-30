package 动态规划.leetcode_397_整数替换;
class Solution {
    public int integerReplacement(int n) {

        return dfs(n);

    }
    private int [] mem;
    private int dfs(int n) {


        if(n == 1) return 0;
        int res = 0;
        if(n%2 == 0) res = dfs(n/2) + 1;
        else {
            res = 2 + Math.min(dfs(n/2), dfs(n / 2 + 1));
        }
        return res ;
    }
}
