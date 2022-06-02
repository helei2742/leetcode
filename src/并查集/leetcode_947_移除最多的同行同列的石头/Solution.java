package 并查集.leetcode_947_移除最多的同行同列的石头;

import java.util.HashMap;
import java.util.Map;

/*
    在同一行和同一列的石头可以移除，
    通过观察发现，将彼此同一行或同一列的石头当作一个整体，
    这个整体最后都会被去除到只剩一个石头，
    一个连通块最终只剩下一块石头

    也就是说移除最多的石头数量 = 石头数量 - 连通块的数量
* */
public class Solution {
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind(20005);
        int len = stones.length;
        for(int i = 0; i < len; i++) {
            int x = stones[i][0];
            int y = stones[i][1];
            unionFind.union(x, y + 10001);
        }
        return len - unionFind.getCount();
    }
    class UnionFind {
        private Map<Integer, Integer> father;
        private int count;
        UnionFind(int n) {
            count = 0;
            father = new HashMap<>();
        }
        public int findF(int x) {
            if(!father.containsKey(x)) {
                father.put(x, x);
                count++;
            }
            if(x != father.get(x))
                father.put(x, findF(father.get(x)));

            return father.get(x);
        }
        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father.put(fA, fB);
            count--;
        }
        public int getCount() {
            return count;
        }
    }

}
