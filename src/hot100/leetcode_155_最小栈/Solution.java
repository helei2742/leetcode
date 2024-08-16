package hot100.leetcode_155_最小栈;

import java.util.Stack;

public class Solution {
}
class MinStack {
    private Stack<int[]> stack;
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        int minVal = val;
        if (!stack.isEmpty()){
            int t = stack.peek()[1];
            if(t < minVal){
                minVal = t;
            }
        }
        stack.push(new int[]{val, minVal});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
