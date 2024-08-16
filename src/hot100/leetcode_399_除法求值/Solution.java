package hot100.leetcode_399_除法求值;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Integer> idxMap = new HashMap<>();
        int len = values.length;
        int idx = 0;
        UnionFind unionFind = new UnionFind(len*2);

        for (int i = 0; i < len; i++) {
            List<String> equation = equations.get(i);
            for (String string : equation) {
                if (!idxMap.containsKey(string)){
                    idxMap.put(string, idx++);
                }
            }
            unionFind.union(idxMap.get(equation.get(0)), idxMap.get(equation.get(1)), values[i]);
        }

        double[] res = new double[queries.size()];
        idx = 0;
        for (List<String> query : queries) {
            Integer u = idxMap.get(query.get(0));
            Integer v = idxMap.get(query.get(1));
            if(u == null || v == null) {
                res[idx] = -1.0;
            }else {
                res[idx] = unionFind.isUnion(u, v);
            }
            idx++;
        }
        return res;
    }

    class UnionFind{
        public int[] father;
        public double[] weight;

        UnionFind(int n) {
            father = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                weight[i] = 1.0;
            }
        }

        public int findF(int x){
            if(x != father[x]){
                int o = father[x];
                father[x] = findF(father[x]);
                weight[x] *= weight[o];
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

        public double isUnion(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA != fB) return -1.0;
            return weight[a] / weight[b];
        }
    }
}
