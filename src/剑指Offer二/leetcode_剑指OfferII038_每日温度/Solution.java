package 剑指Offer二.leetcode_剑指OfferII038_每日温度;

import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int len = temperatures.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            while(!stack.empty()&&(temperatures[stack.peek()] < temperatures[i])){
                int top = stack.pop();
                res[top] = i - top;
            }
            stack.push(i);
        }
        return res;
    }
}
