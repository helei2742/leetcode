package 动态规划.leetcode_85_最大矩形;

public class Solution {


    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //计算从i j 开始左边有多少个连续的1
        int[][] left = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0?0:left[i][j-1])+1;
                }
            }
        }

        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == '1'){
                    int minWidth = left[i][j];
                    int maxArea = minWidth;
                    for (int k = i-1; k >=0 ; k--) {
                        minWidth = Math.min(minWidth, left[k][j]);
                        maxArea = Math.max(maxArea, (i-k+1)*minWidth);
                    }
                    res = Math.max(res, maxArea);
                }
            }
        }

        return res;
    }

}
