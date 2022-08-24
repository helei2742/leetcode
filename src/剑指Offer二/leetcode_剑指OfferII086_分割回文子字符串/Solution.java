package 剑指Offer二.leetcode_剑指OfferII086_分割回文子字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String[][] partition(String s) {
        int len = s.length();
        t = new ArrayList<>();
        res = new ArrayList<>();
        dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], true);
        }

        for (int i = len-1; i >= 0; i--) {
            for (int j = i+1; j < len; j++) {
                dp[i][j] = (s.charAt(i)==s.charAt(j)&&dp[i+1][j-1]);
            }
        }

        dfs(s, 0);

        int size = res.size();
        String[][] ans = new String[size][];

        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i).toArray(new String[]{});
        }
        return ans;
    }
    private List<String> t;
    private List<List<String>> res;
    private boolean[][] dp;
    private void dfs(String s, int idx) {
        if(idx == s.length()) {
            res.add(new ArrayList<>(t));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            if(dp[idx][i]){
                t.add(s.substring(idx, i + 1));
                dfs(s, i + 1);
                t.remove(t.size() - 1);
            }
        }
    }
}

class Solution2 {
    public String[][] partition(String s) {

        int len = s.length();
        mem = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(mem[i], -1);
        }
        t = new ArrayList<>();
        res = new ArrayList<>();
        dfs(s, 0);

        int size = res.size();
        String[][] ans = new String[size][];

        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i).toArray(new String[]{});
        }
        return ans;
    }
    private List<String> t;
    private List<List<String>> res;
    private void dfs(String s, int idx) {
        if(idx == s.length()) {
            res.add(new ArrayList<>(t));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            if(isPartition(s, idx, i) == 1){
                t.add(s.substring(idx, i + 1));
                dfs(s, idx + 1);
                t.remove(t.size()-1);
            }
        }
    }

    private int[][] mem;
    private int isPartition(String s, int l, int r) {
        if(mem[l][r] != -1) return mem[l][r];

        if(l == r){
            mem[l][r] = 1;
        }else if(s.charAt(l) == s.charAt(r)){
            mem[l][r] = isPartition(s, l+1, r-1);
        }else {
            mem[l][r] = 0;
        }
        return mem[l][r];
    }
}
