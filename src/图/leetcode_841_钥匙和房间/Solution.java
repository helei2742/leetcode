package 图.leetcode_841_钥匙和房间;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        isVisited = new boolean[n];
        Arrays.fill(isVisited, false);

        dfs(rooms, 0);
        return n==count;
    }

    private boolean[] isVisited;
    private int count = 0;
    private void dfs(List<List<Integer>> rooms, int cur){
        if(isVisited[cur]){
            return;
        }
        isVisited[cur] = true;
        count++;
        for (Integer next : rooms.get(cur)) {
            dfs(rooms, next);
        }
    }
}