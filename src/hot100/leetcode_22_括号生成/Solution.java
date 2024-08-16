package hot100.leetcode_22_括号生成;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        sb = new StringBuilder();
        res = new ArrayList<>();
        dfs(0,0, n);
        return res;
    }
    StringBuilder sb;
    List<String> res;
    private void dfs(int left, int right, int n){
        if(left+right == n + n){
            res.add(sb.toString());
            return;
        }
        if(left < n){
            sb.append('(');
            dfs(left+1, right, n);
            sb.deleteCharAt(sb.length()-1);
        }
        if(right < left){
            sb.append(')');
            dfs(left, right + 1, n);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
