package 剑指offer.leetcode_剑指Offer29_顺时针打印矩阵;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null||matrix.length<=0||matrix[0].length==0) return new int[0];
        int row = matrix.length;
        int col = matrix[0].length;
        int times = Math.min(row, col);
        if(times!=1){
            if(times%2==0&&times!=2) times--;
            times--;
        }

        List<Integer> res = new ArrayList<>(row*col);
        int count = 0;
        while (count<times){
            for (int i = count; i < col - count; i++) {
                res.add(matrix[count][i]);
            }
            for (int i = count+1; i < row-count; i++) {
                res.add(matrix[i][col-count-1]);
            }

            for (int i = col-count-2; i > count; i--) {
                res.add(matrix[row-count-1][i]);
            }
            for (int i = row-count-1; i > count; i--) {
                res.add(matrix[i][count]);
            }
            count++;
        }

        int[] ans = new int[row*col];
        for (int i = 0; i < row*col; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
