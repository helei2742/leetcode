package hot100.leetcode_17_电话号码的字母组合;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if(digits.length()==0) return res;
        sb = new StringBuilder();
        dfs(digits, 0);
        return res;
    }
    private char[][] chars = new char[][]{{'a','b','c'}, {'d','e','f'},
            {'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
    };

    List<String> res;
    StringBuilder sb;
    private void dfs(String str, int idx) {
        if(idx == str.length()) {
            res.add(sb.toString());
            return;
        }
        int charIdx = str.charAt(idx) - '0' - 2;
        for (char c : chars[charIdx]) {
            sb.append(c);
            dfs(str, idx+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
