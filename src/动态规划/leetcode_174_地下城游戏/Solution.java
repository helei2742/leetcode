package 动态规划.leetcode_174_地下城游戏;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        row = dungeon.length;
        col =  dungeon[0].length;
//        mem = new HashMap<>();
//
//        return dfs(dungeon, 0, 0);

        return dp(dungeon);
    }

    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

    private Map<Integer, Integer> mem;
    //dfs 返回 x ，y 到 重点所需要的最小生命值
    private int dfs(int[][] map, int x, int y) {
        if(!isArea(x, y)) return Integer.MAX_VALUE;

        if(mem.containsKey(x*col+y)) return mem.get(x*col+y);

        if (x == row - 1 && y == col - 1) {
            if (map[x][y] >= 0) {
                // 到最后一个格子，大于0 则最少需要1点生命值就行
                return 1;
            } else {
                // 否则需要比房间怪物生命值大1
                return -map[x][y] + 1;
            }
        }
        int res = 0;
        int left = dfs(map, x, y+1);
        int down = dfs(map, x+1, y);
        //需要的生命值等于两边最少需要的生命值 减去 当前地能回复的值（如果是守卫则所需生命增加）
        res = Math.min(left, down) - map[x][y];
        //所需生命值负的，，也就是0也可以，但骑士生命值不能小于1
        if(res <= 0) {
            res = 1;
        }

        mem.put(x*col+y, res);
        return res;
    }


    private int dp(int[][] dungeon){
        int[][] dp = new int[row+1][col+1];
        for (int i = 0; i <= row; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[row-1][col] = 1;
        dp[row][col-1] = 1;

        for (int i = row-1; i >=0; i--) {
            for (int j = col-1; j >=0; j--){
                int temp = Math.min(dp[i-1][j], dp[i][j-1]) - dungeon[i][j];
                dp[i][j] = Math.max(temp, 1);
            }
        }
        return dp[0][0];
    }
}
