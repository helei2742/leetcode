package 剑指Offer二.leetcode_剑指OfferII111_计算除法;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int size = equations.size();
        UnionFound unionFound = new UnionFound(2*size);
        Map<String, Integer> idxMap = new HashMap<>();
        int idx = 0;
        int i = 0;
        for (List<String> equation : equations) {
            for (String s : equation) {
                if(!idxMap.containsKey(s)){
                    idxMap.put(s, idx++);
                }
            }
            unionFound.union(idxMap.get(equation.get(0)), idxMap.get(equation.get(1)), values[i++]);
        }
        double[] res = new double[queries.size()];
        i = 0;
        for (List<String> query : queries) {
            Integer idx1 = idxMap.get(query.get(0));
            Integer idx2 = idxMap.get(query.get(1));
            if(idx1 == null  || idx2 == null){
                res[i++] = -1.0d;
            }else {
                res[i++] = unionFound.isConnect(idx1, idx2);
            }
        }
        return res;
    }

    class UnionFound{
        private int[] father;
        private double[] weight;
        UnionFound(int n){
            father = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                weight[i] = 1.0d;
            }
        }
        public int findF(int x) {
            if(father[x] != x){
                int o = father[x];
                father[x] = findF(father[x]);
                weight[x] *= weight[o];
            }
            return father[x];
        }

        public void union(int a, int b, double value) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;;
            father[fA] = fB;
            weight[fA] = weight[b] * value / weight[a];
        }
        public double isConnect(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB){
                return weight[a]/weight[b];
            }else {
                return -1.0d;
            }
        }
    }
}
