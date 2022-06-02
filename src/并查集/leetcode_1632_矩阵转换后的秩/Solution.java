package 并查集.leetcode_1632_矩阵转换后的秩;

import java.util.Arrays;

public class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int size = rows*cols;

        Integer[] indexs = new Integer[size];

        for (int i = 0; i < size; i++) {
            indexs[i] = i;
        }

        //按照在矩阵中的值进行从小到大排序
        Arrays.sort(indexs, (o1, o2) -> matrix[o1/cols][o1%cols] - matrix[o2/cols][o2%cols]);

        //存储i行中最大秩所在的列
        int[] maxRow = new int[rows];
        //存储i列中最大秩所在的行
        int[] maxCol = new int[cols];
        Arrays.fill(maxCol,-1);
        Arrays.fill(maxRow,-1);

        UnionFind unionFind = new UnionFind(size+1);


        int[] indexToValue = new int[size];

        //由于已经排序过，后面加入的秩一定大于或等于行或列中的最大的秩
        for (int pos = 0; pos < size; pos++) {
            int val = 1;
            //按照原矩阵中取出对应的点坐标，x，y
            int index = indexs[pos];
            int x = index/cols;
            int y = index%cols;

            //x行有最大
            if(maxRow[x] != -1) {
                //取出x行最大的秩所在的列坐标j
                int j = maxRow[x];
                //得到x行最大秩处的索引 tempIndex
                int tempIndex = x * cols + j;
                //与该引索处值相等的同行、列的索引
                int tempRoot = unionFind.find(tempIndex);
                //该索引处的值
                int tempVal = indexToValue[tempRoot];

                if(matrix[x][y] == matrix[x][j]){
                    //值相等，后面加入的同行元素的秩可会与前面加入的不一样，需将在源矩阵中值相等的同行同列元素加入到一个并查集中
                    //供后面跟更新使用。
                    unionFind.union(index, tempIndex);
                    val = Math.max(val, tempVal);
                }else {
                    val = Math.max(val, tempVal + 1);
                }
            }

            //列同理
            if(maxCol[y] != -1) {
                int i = maxCol[y];
                int tempIndex = i * cols + y;
                int tempRoot = unionFind.find(tempIndex);
                int tempVal = indexToValue[tempRoot];
                if(matrix[x][y] == matrix[i][y]) {
                    unionFind.union(index, tempIndex);
                    val = Math.max(val, tempVal);
                }else {
                    val = Math.max(val, tempVal + 1);
                }
            }

            maxCol[y] = x;
            maxRow[x] = y;
            int root = unionFind.find(index);

            //跟新秩值
            indexToValue[root] = val;
        }

        int[][] ans = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                int root = unionFind.find(index);
                ans[i][j] = indexToValue[root];
            }
        }

        return ans;
    }

    class UnionFind {
        private final int [] f;
        UnionFind(int n) {
            f = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
            }
        }
        public int find(int x) {
            if(x != f[x]) f[x] = find(f[x]);
            return  f[x];
        }
        public void union(int a, int b) {
            int fA = find(a);
            int fB = find(b);
            if(fA == fB) return;
            f[fA] = fB;
        }
    }
}
