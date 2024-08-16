package hot100.leetcode_20_有效的括号;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c == ')') {
                if(stack.empty() || stack.peek() != '('){
                    return false;
                }else {
                    stack.pop();
                }
            }else if(c == ']'){
                if(stack.empty() || stack.peek() != '['){
                    return false;
                }else {
                    stack.pop();
                }
            }else if(c == '}'){
                if(stack.empty() || stack.peek() != '{'){
                    return false;
                }else {
                    stack.pop();
                }
            }else {
                stack.push(c);
            }
        }
        return stack.size() == 0;
    }
}
