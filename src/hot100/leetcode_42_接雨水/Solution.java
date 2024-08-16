package hot100.leetcode_42_接雨水;

import java.util.Stack;

class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        int[] rightMax = new int[len];
        rightMax[len-1] = height[len-1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            res += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }
        return res;
    }



    private int fn(int[] height) {
        int len = height.length;

        Stack<Integer> stack = new Stack<>();

        int res = 0;
        for (int i = 0; i < len; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if(stack.isEmpty()) break;
                int left = stack.peek();
                int width = i - left - 1;
                int h = Math.min(height[i], height[left])  - height[top];
                res += width * h;
            }
            stack.add(i);
        }
        return res;
    }
}
