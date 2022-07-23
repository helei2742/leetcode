package 剑指offer.leetcode_剑指Offer31_栈的压入弹出序列;

import java.util.Stack;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int idxPopped = 0;
        for (int num : pushed) {
            stack.push(num);

            while (!stack.empty()){
                if(stack.peek() == popped[idxPopped]){
                    stack.pop();
                    idxPopped++;
                }else{
                    break;
                }
            }
        }
        return stack.size() == 0;
    }
}