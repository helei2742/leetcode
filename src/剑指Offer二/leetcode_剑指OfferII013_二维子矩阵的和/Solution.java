package 剑指Offer二.leetcode_剑指OfferII013_二维子矩阵的和;

class NumMatrix {
    private int[][] preSum = null;
    private int row = 0;
    private int col = 0;
    public NumMatrix(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        preSum = new int[row][col];
        preSum[0][0] = matrix[0][0];
        for (int i = 1; i < col; i++) {
            preSum[0][i] = preSum[0][i-1]+matrix[0][i];
        }
        for (int i = 1; i < row; i++) {
            preSum[i][0] = preSum[i-1][0]+matrix[i][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                preSum[i][j] = preSum[i][j-1]+preSum[i-1][j]-preSum[i-1][j-1]+matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int l = col1==0?0:preSum[row2][col1-1];
        int t = row1==0?0:preSum[row1-1][col2];
        int lt = (col1!=0&&row1!=0)?preSum[row1-1][col1-1]:0;

        int res = preSum[row2][col2] - l - t + lt;
        return res;
    }
}

