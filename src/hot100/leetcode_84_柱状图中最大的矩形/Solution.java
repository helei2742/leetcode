package hot100.leetcode_84_柱状图中最大的矩形;

import java.util.Stack;

class Solution {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[len];

        for (int i = 0; i < len; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }
        stack.clear();

        int[] right = new int[len];
        for (int i = len-1; i >= 0; i--) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            right[i] = stack.isEmpty()?len:stack.peek();
            stack.push(i);
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }
}
