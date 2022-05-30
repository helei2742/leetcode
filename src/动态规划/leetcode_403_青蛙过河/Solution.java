package 动态规划.leetcode_403_青蛙过河;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if(n==1) return true;
        mem = new Boolean[n][n];
        return dfs(stones,0,0);
    }
    private Boolean[][] mem;
    private boolean dfs(int[] stones, int index, int distance){
        if(index == stones.length-1) return true;
        if(mem[index][distance] != null) return mem[index][distance];

        boolean res = false;
        for (int i = index+1; i < stones.length; i++) {
            int len = stones[i] - stones[index];
            if(len >= distance-1 && len <= distance+1){
                if(dfs(stones, i, len)){
                    res = true;
                    break;
                }
            }
            if(len > distance+1) break;
        }

        return mem[index][distance] = res;
    }
}
