package hot100.leetcode_301_删除无效的括号;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int len = s.length();
        res = new ArrayList<>();

        int rCanMove = 0, lCanMove = 0;
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == '(') {
                lCanMove++;
            }else if(s.charAt(i) == ')'){
                if(lCanMove == 0){
                    rCanMove++;
                }else {
                    lCanMove--;
                }
            }
        }
        dfs(s, 0, lCanMove,rCanMove);
        return res;
    }

    private List<String> res;
    private void dfs(String s, int idx, int lCanMove, int rCanMove) {
        if(lCanMove == 0 && rCanMove == 0) {
            if(isValid(s)){
                res.add(s);
            }
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if(i != idx && s.charAt(i) == s.charAt(i-1)) continue;;
            if(lCanMove > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0,i)+s.substring(i+1), i, lCanMove-1,rCanMove);
            }

            if(rCanMove > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0,i)+s.substring(i+1), i, lCanMove, rCanMove-1);
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                count++;
            }else if(s.charAt(i) == ')'){
                count--;
                if(count < 0)
                    return false;
            }
        }
        return count == 0;
    }
}
