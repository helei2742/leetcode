package 动态规划.leetcode_221_最大正方形;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix==null||matrix.length<1||matrix[0].length<1) return 0;
        row = matrix.length;
        col = matrix[0].length;

        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(ans, maxZFX(matrix, i, j));
            }
        }
        return ans;
    }
    private int row;
    private int col;
    private int maxZFX(char[][] graph, int x, int y){
        int ans = 1;
        int i = x, j = y;
        while(i<row&&j<col&&graph[i][j] == '1'){
            boolean f = true;
            for (int k = 0; k <= i - x; k++){

                if(graph[i][k + y] == '0' || graph[x+k][j] == '0'){
                    f = false;
                    break;
                }
            }
            if(!f){
                break;
            }
            i++;
            j++;
            ans = (i-x) * (j-y);
        }
        return ans;
    }
}
