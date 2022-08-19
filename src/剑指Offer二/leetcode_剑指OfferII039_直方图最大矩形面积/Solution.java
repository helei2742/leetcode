package 剑指Offer二.leetcode_剑指OfferII039_直方图最大矩形面积;

import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        Stack<Integer> stack = new Stack<>();
        //left[i] 表示i位置的左边第一个高度小于i位置高度的下标
        int[] left = new int[len], right = new int[len];

        for (int i = 0; i < len; i++) {
            while(!stack.empty() && (heights[stack.peek()] >= heights[i])){
                stack.pop();
            }
            left[i] = stack.empty()?-1:stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            while(!stack.empty() && (heights[stack.peek()] >= heights[i])) {
                stack.pop();
            }
            right[i] = stack.empty()?len:stack.peek();
            stack.push(i);
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, heights[i]*(right[i] - left[i] - 1));
        }
        return res;
    }
}