package hot100.leetcode_85_最大矩形;

import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int row, col;
        if(matrix == null || (row=matrix.length) == 0 || (col=matrix[0].length) == 0){
            return 0;
        }
        int[][] left = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == '1'){
                    left[i][j] = j==0?1:left[i][j-1]+1;
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < col; i++) {
            int[] up = new int[row];
            for (int j = 0; j < row; j++) {
                while (!stack.isEmpty() && left[stack.peek()][i] >= left[j][i]) {
                    stack.pop();
                }
                up[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            stack.clear();
            int[] down = new int[row];
            for (int j = row-1; j >= 0; j--) {
                while (!stack.isEmpty() && left[stack.peek()][i] >= left[j][i]) {
                    stack.pop();
                }
                down[j] = stack.isEmpty() ? row : stack.peek();
                stack.push(j);
            }
            stack.clear();
            for (int j = 0; j < row; j++) {
                res = Math.max(res, (down[j]-up[j]-1)*left[j][i]);
            }
        }
        return res;
    }
}
