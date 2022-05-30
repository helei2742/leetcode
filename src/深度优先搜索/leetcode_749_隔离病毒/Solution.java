package 深度优先搜索.leetcode_749_隔离病毒;

import java.util.*;

class Solution {
    private int [][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    private boolean isArea(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

    private int row;
    private int col;
    private boolean[][] isVisited;

    private int getIndex(int x, int y){
        return x*col + y;
    }


    private HashMap<Integer, HashSet<Integer>> areaBorder = new HashMap<>();
    private HashMap<Integer, List<Integer>> areaPos = new HashMap<>();
    private HashMap<Integer, Integer> areaMapBorderLen = new HashMap<>();
    private int infectedAreaCount = 0;

    public int containVirus(int[][] isInfected) {
        row = isInfected.length;
        col = isInfected[0].length;
        if(row == 1&&col==1) return 0;

        isVisited = new boolean[row][col];

        boolean isAllInfected = false;
        int ans = 0;
//        System.out.println("====");
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                System.out.print(isInfected[i][j] + "  ");
//            }
//            System.out.print('\n');
//        }
//        System.out.println("====");
        while(!isAllInfected){
//            System.out.println("执行一次");
            isAllInfected = true;
            infectedAreaCount = 0;

            areaBorder.clear();
            areaMapBorderLen.clear();
            areaPos.clear();

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    isVisited[i][j] = false;
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(isInfected[i][j] == 1 && !isVisited[i][j]){
                        infectedAreaCount++;
                        areaBorder.put(infectedAreaCount, new HashSet<>());
                        areaPos.put(infectedAreaCount, new ArrayList<>());
                        int borderLen = calcInfectBlock(isInfected, i, j, infectedAreaCount);
                        areaMapBorderLen.put(infectedAreaCount, borderLen);
                    }
                }
            }

            //找到能感染最多的区域
            Set<Integer> areas = areaBorder.keySet();
            int max = -1;
            int targetArea = -1;
            for (Integer area : areas) {
                if(areaBorder.get(area).size()>max){
                    targetArea = area;
                    max = areaBorder.get(area).size();
                }
            }
            ans += areaMapBorderLen.get(targetArea);

            //将该区域封锁
            List<Integer> maxAreaBorder = areaPos.get(targetArea);
            for (Integer index : maxAreaBorder) {
                int x = index / col;
                int y = index % col;
                isInfected[x][y] = -1;
            }

            //其他区域扩张
            for (Integer area : areas) {
                if(area != targetArea){
                    HashSet<Integer> borders = areaBorder.get(area);
                    for (Integer index : borders) {
                        int x = index / col;
                        int y = index % col;
                        isInfected[x][y] = 1;
                    }
                }
            }
//            System.out.println("====");
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    System.out.print(isInfected[i][j] + "  ");
//                }
//                System.out.print('\n');
//            }
//            System.out.println("====");

            //判断是否全部被感染
            //记录是否还有为1的 （为被隔离的感染区域）
            boolean f = false;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(isInfected[i][j] == 1)
                        f = true;
                    if(isInfected[i][j] == 0){
                        isAllInfected = false;
                    }
                }
            }
            if(!f) break;
        }

        return ans;
    }

    private int calcInfectBlock(int[][] graph, int x, int y, int areaCount){
        if(isVisited[x][y]){
            return 0;
        }
        isVisited[x][y] = true;
        if(graph[x][y]==0) return 0;
        areaPos.get(areaCount).add(getIndex(x, y));

        int res = 0;
        for (int[] direction : directions) {
            int nX = x + direction[0];
            int nY = y + direction[1];
            if(isArea(nX,nY) && graph[nX][nY] == 1){
                res += calcInfectBlock(graph, nX, nY, areaCount);
            }else if(isArea(nX,nY) && graph[nX][nY] == 0){
                HashSet<Integer> borders = areaBorder.get(areaCount);
                int index = getIndex(nX, nY);
                borders.add(index);
                res++;
            }
        }
        return res;
    }
}