package 剑指Offer二.leetcode_剑指OfferII040_矩阵中最大的矩形;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int maximalRectangle(String[] matrix) {
        int row = 0;
        int col = 0;
        if(matrix==null|| (row=matrix.length)==0 || (col=matrix[0].length())==0)
            return 0;

        int[][] left = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i].charAt(j) == '1'){
                    left[i][j] = j==0?1:left[i][j-1]+1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i].charAt(j) == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i-1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i-k+1) * width);
                }
                res = Math.max(res, area);
            }
        }
        return res;
    }
    
    public int fn(String[] matrix) {
        int row = 0;
        int col = 0;
        if(matrix==null|| (row=matrix.length)==0 || (col=matrix[0].length())==0)
            return 0;

        int[][] left = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i].charAt(j) == '1'){
                    left[i][j] = j==0?1:left[i][j-1]+1;
                }
            }
        }


        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < col; i++) {
            int[] up = new int[row], down = new int[row];

            stack.clear();
            for (int j = 0; j < row; j++) {
                while(!stack.empty() && (left[stack.peek()][i] >= left[j][i])) {
                    stack.pop();
                }
                up[j] = stack.empty()?-1:stack.peek();
                stack.push(j);
            }

            stack.clear();
            for (int j = row - 1; j >= 0; j--) {
                while (!stack.empty() && (left[stack.peek()][i] >= left[j][i])) {
                    stack.pop();
                }
                down[j] = stack.empty()?row:stack.peek();
                stack.push(j);
            }

            for (int j = 0; j < row; j++) {
                res = Math.max(res, (down[j] - up[j] - 1) * left[j][i]);
            }
        }
        return res;
    }
}
