package 图.leetcode_1368_使网格图至少有一条有效路径;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    private int row;
    private int col;

    private static final int[][] DIRECTIONS = {{0,1},{0,-1},{1,0},{-1,0}};

    private static final int INF = Integer.MAX_VALUE/2;
    public int minCost(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int[] dis = new int[row*col];
        boolean[] isVisited = new boolean[row*col];

        Arrays.fill(dis, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);

        pq.offer(new int[]{0, 0});
        dis[0] = 0;
        while (!pq.isEmpty()){
            int[] head = pq.poll();
            int curIndex = head[0];

            if(isVisited[curIndex]) continue;
            isVisited[curIndex] = true;

            int x = curIndex/col;
            int y = curIndex%col;
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int nextX = x + DIRECTIONS[i][0];
                int nextY = y + DIRECTIONS[i][1];
                int nextIndex = nextX*col+nextY;
                int weight = (i == grid[x][y]-1)?0:1;
                if(isArea(nextX, nextY) && dis[nextIndex] > dis[curIndex] + weight){
                    dis[nextIndex] = dis[curIndex] + weight;
                    pq.offer(new int[]{nextIndex, dis[nextIndex]});
                }
            }
        }

        return dis[row*col-1];
    }

    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

}