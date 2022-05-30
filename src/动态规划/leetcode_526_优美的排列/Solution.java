package 动态规划.leetcode_526_优美的排列;

public class Solution {
    public int countArrangement(int n) {

//        isVisited = new boolean[n+1];
//
//        return dfs(1, n);

        return dp2(n);
    }
    private boolean[] isVisited;
    private int dfs(int index, int n){
        if(index > n){
            return 1;
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if(!isVisited[index] && (i%index == 0 || index%i == 0)){
                isVisited[i] = true;
                res += dfs(index+1, n);
                isVisited[i] = false;
            }
        }
        return res;
    }

    private int parsedfs(int index, int n, int visited){
        if(index > n) return 1;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if(((1<<i)&visited)==0 && (i%index==0||index%i==0)){
                visited = (1<<i)|visited;
                res += parsedfs(index+1,n,visited);
            }
        }
        return res;
    }

    private int dp(int n) {
        int mask = 1<<n;
        int[][] dp = new int[n+1][mask+1];

        dp[0][0] = 1;
        for (int index = 1; index <= n; index++) {
            for (int visited = 0; visited < mask; visited++) {
                //visited的1的位数index一定相当的
                if(Integer.bitCount(visited) == index){
                    for (int num = 1; num <= n; num++) {
                        if((1<<(num-1)&visited)!=0 && (num%index==0||index%num==0)){
                            dp[index][visited]+=dp[index-1][visited&(~(1<<(num-1)))];
                        }
                    }
                }
            }
        }
        return dp[n][mask-1];
    }

    private int dp2(int n) {
        int mask = 1<<n;
        int[] dp = new int[mask+1];
        dp[0] = 1;
        for (int visited = 0; visited < mask; visited++) {
            //visited的1的位数index一定相当的
            int index = Integer.bitCount(visited);
            for (int num = 1; num <= n; num++) {
                if((1<<(num-1)&visited)!=0 && (num%index==0||index%num==0)){
                    dp[visited]+=dp[visited&(~(1<<(num-1)))];
                }
            }
        }
        return dp[mask-1];
    }
}
