package 并查集.leetcode_339_除法求值;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class
Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> map = new HashMap<>();
        int size = equations.size();
        UnionFind unionFind = new UnionFind(2*size);

        int index = 0;
        int i = 0;
        for (List<String> equation : equations) {
            for (String query : equation) {
                if(!map.containsKey(query)){
                    map.put(query, index++);
                }
            }
            unionFind.union(map.get(equation.get(0)), map.get(equation.get(1)), values[i++]);
        }

        double[] anser = new double[queries.size()];
        i = 0;
        for (List<String> query : queries) {
            String s1 = query.get(0);
            String s2 = query.get(1);
            Integer index1 = map.get(s1);
            Integer index2 = map.get(s2);

            if(index1 == null || index2 == null)
                anser[i++] = -1.0d;
            else {
                anser[i++] = unionFind.isConnect(index1, index2);
            }
        }

        return anser;
    }
    class UnionFind {
        private int [] father;
        private double [] weight;
        UnionFind(int n) {
            father = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                weight[i] = 1.0d;
            }
        }
        public int findF(int x) {
            if(x != father[x]) {
                int origin = father[x];
                father[x] = findF(father[x]);
                weight[x] *= weight[origin];
            }
            return father[x];
        }
        public void union(int a, int b, double value) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;

            father[fA] = fB;
            weight[fA] = weight[b] * value / weight[a];
        }
        public double isConnect(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) {
                return weight[a]/weight[b];
            }else {
                return -1.0d;
            }
        }
    }
}
