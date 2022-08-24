package 剑指Offer二.leetcode_剑指OfferII085_生成匹配的括号;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(new char[n*2], 0);
        return res;
    }
    List<String> res;
    private void dfs(char[] chars, int idx) {
        if(idx == chars.length){
            if(isAC(chars)){
                res.add(new String(chars));
            }
            return;
        }
        chars[idx] = '(';
        dfs(chars, idx+1);
        chars[idx] = ')';
        dfs(chars, idx+1);
    }
    private boolean isAC(char[] chars){
        int t = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] =='(') t++;
            else t--;

            if (t < 0) {
                return false;
            }
        }
        return t==0;
    }
}

class Solution2 {
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(new StringBuilder(), n, 0, 0);
        return res;
    }
    List<String> res;
    private void dfs(StringBuilder sb, int n, int open, int close) {
        if(sb.length() == n*2){
            res.add(sb.toString());
        }

        if(open < n) {
            sb.append('(');
            dfs(sb, n, open+1, close);
            sb.deleteCharAt(sb.length()-1);
        }

        if(close < open) {
            sb.append(')');
            dfs(sb, n, open, close+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
