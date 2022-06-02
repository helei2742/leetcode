package 并查集.leetcode_1722_执行交换后的最小汉民距离;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

        int n = source.length;

        UnionFind unionFind = new UnionFind(n+1);

        /*
            以在数组中的索引建立并查集，
            由于俩俩可交换，因此并查集中所有的索引下的元素都可任意交换，关键在于其元素出现的次数
        * */
        for(int i = 0; i < allowedSwaps.length; i++) {
            int a = allowedSwaps[i][0];
            int b = allowedSwaps[i][1];
            unionFind.union(a, b);
        }
        //   根           数值      次数
        Map<Integer, Map<Integer,Integer>> s = new HashMap<>();
        //建立映射
        for (int i = 0; i < n; i++) {
            int root = unionFind.findF(i);
            if(!s.containsKey(root)) {
                s.put(root, new HashMap<>());
            }
            s.get(root).put(source[i], s.get(root).getOrDefault(source[i], 0) + 1);
        }

        //将target数组中 逐位与建立的映射中比较
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int root = unionFind.findF(i);
            //该索引处在并查集中能交换的不包含target[i]的数值， 或者包含了，但是被使用完了
            //无可奈何，答案加1
            if((!s.get(root).containsKey(target[i])) || (s.get(root).get(target[i]) == 0)) {
                ans++;
            }else {
                //包含，将这个根下的target[i]的数值数量减一
                s.get(root).put(target[i], s.get(root).get(target[i])-1);
            }
        }

        return ans;
    }
    class UnionFind {
        private final int[] father;
        private final int[] rank;
        UnionFind(int n) {
            father = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                rank[i] = 1;
            }
        }
        public int findF(int x) {
            if(x != father[x]) father[x] = findF(father[x]);
            return father[x];
        }
        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            rank[fB] += rank[fA];
            father[fA] = fB;
        }
        public int getSize(int x) {
            int fX = findF(x);
            return rank[fX];
        }
    }
}