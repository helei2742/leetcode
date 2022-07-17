package 剑指offer.leetcode_剑指Offer30_包含min函数的栈;

import java.util.ArrayList;
import java.util.List;

class MinStack {
    private List<Integer> stack = new ArrayList<>();
    private List<Integer> minStack = new ArrayList<>();
    private int top = -1;
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        top++;
        System.out.println(top);
        stack.add(top, x);
        minStack.add(top, Math.min(top==0?Integer.MAX_VALUE:minStack.get(top-1), x));
    }

    public void pop() {
        minStack.remove(top);
        stack.remove(top);
        top--;
    }

    public int top() {
        return stack.get(top);
    }

    public int min() {
        return minStack.get(top);
    }
}
