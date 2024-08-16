package 剑指Offer二.leetcode_剑指OfferII098_路径的数目;

class Solution {
    public int uniquePaths(int m, int n) {
        this.row = m;
        this.col = n;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        dp[0][0] = 0;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }
}
