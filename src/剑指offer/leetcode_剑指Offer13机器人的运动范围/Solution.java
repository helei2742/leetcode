package 剑指offer.leetcode_剑指Offer13机器人的运动范围;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int movingCount(int m, int n, int k) {
        this.row = m;
        this.col = n;
        this.k = k;
//        return  dfs(0,0, new boolean[m*n]);
        return bfs();
    }
    private final static int[][] DIRECTIONS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    private int row;
    private int col;
    private int k;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

    public boolean isBitSumK(int a, int b,int k){
        int sum = 0;
        while (a%10 != 0 || b%10 != 0){
            sum += a%10 + b%10;
            a /= 10;
            b /= 10;
        }
        return k>=sum;
    }

    private int dfs(int x, int y, boolean[] v){
        int index = x*col + y;
        if(v[index]) return 0;
        v[index] =true;
        int res = 1;
        for (int[] direction : DIRECTIONS) {
            int nx = x+direction[0];
            int ny = y+direction[1];
            if(isArea(nx,ny) && isBitSumK(nx, ny, k)){
                res += dfs(nx, ny, v);
            }
        }
        return res;
    }
    private int bfs() {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[row*col];
        queue.offer(0);

        int res = 0;
        while (!queue.isEmpty()){
            Integer idx = queue.poll();

            if (isVisited[idx]) continue;
            isVisited[idx] = true;
            res ++;
            int x = idx/col;
            int y = idx%col;
            for (int[] direction : DIRECTIONS) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                if(isArea(nx,ny) && isBitSumK(nx,ny,k)){
                    queue.add(nx*col+ny);
                }
            }
        }
        return res;
    }
}
