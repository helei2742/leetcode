package 深度优先搜索.leetcode_733_图像渲染;

public class Solution {
    private final int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    private int row;
    private int col;
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        row = image.length;
        col = image[0].length;

        dfs(image,sr,sc, image[sr][sc],newColor);
        return image;
    }

    private void dfs(int[][] graph, int x, int y, int targetVal, int newVal){
        if(graph[x][y] != targetVal) return;
        graph[x][y] = newVal;

        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(isArea(nextX, nextY))
                dfs(graph,nextX,nextY,targetVal,newVal);
        }
    }
}
