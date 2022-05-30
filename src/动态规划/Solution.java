package 动态规划;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        char[] state = new char[2 * n];
        List<String> result = new ArrayList<>();

        dfs(state, 0, result);
        return result;
    }
    // state表示生成括号的模板，index表示当前在哪个位置，result存放结果字符串
    private void dfs(char[] state, int index, List<String> result){
        if(index == state.length){
            //当走到模板最后一位，并且符合条件，加入结果集
            if(able(state)) {
                result.add(new String(state));
            }
        }else{
            state[index] = '(';
            dfs(state, index + 1, result);
            state[index] = ')';
            dfs(state, index + 1, result);
        }
    }

    private boolean able(char[] state) {
        int pos = 0;
        for (char c : state) {
            if(c == '('){
                pos++;
            }else{
                pos--;
            }

            if(pos<0) return false;
        }
        return pos == 0;
    }
}
