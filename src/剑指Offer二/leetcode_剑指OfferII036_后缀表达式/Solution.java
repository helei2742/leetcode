package 剑指Offer二.leetcode_剑指OfferII036_后缀表达式;

import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int len = tokens.length;
        for (int i = 0; i < len; i++) {
            String token = tokens[i];
            if(isNumber(token)){
                stack.push(Integer.parseInt(token));
            }else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                int t = 0;
                switch (token){
                    case "+":
                        t = num1+num2;
                        break;
                    case "-":
                        t = num1-num2;
                        break;
                    case "*":
                        t = num1*num2;
                        break;
                    case "/":
                        t = num1/num2;
                        break;
                }
                stack.push(t);
            }
        }
        return stack.pop();
    }
    private boolean isNumber(String s){
        return !s.equals("+")&&!s.equals("-")&&!s.equals("*")&&!s.equals("/");
    }
}