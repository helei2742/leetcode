package 动态规划.leetcode_131_分割回文字符串;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public List<List<String>> partition(String s) {
        n = s.length();
        res = new ArrayList<>();

        dfs(s.toCharArray(),0,new ArrayDeque<>());
        return res;
    }

    private List<List<String>> res;
    private int n;

    private void dfs(char[] s, int index, Deque<String> strs) {
        if(index == n){
            res.add(new ArrayList<>(strs));
            return;
        }

        for (int i = index; i < n; i++) {
            if(!isHUIWEN(s, index, i)){
                continue;
            }
            strs.addLast(new String(s, index+1,i + 1 - index));
            dfs(s,i+1, strs);
            strs.removeLast();
        }
    }

    private boolean isHUIWEN(char[] charArray, int left, int right){
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
