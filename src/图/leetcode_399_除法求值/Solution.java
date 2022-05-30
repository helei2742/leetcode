package 图.leetcode_399_除法求值;

import java.util.HashMap;
import java.util.List;

public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int m = equations.size();
        UnionFind unionFind = new UnionFind(2*m);
        HashMap<String, Integer> map = new HashMap<>();

        int id = 0;
        for (int i = 0; i < m; i++) {
            List<String> list = equations.get(i);
            String a = list.get(0);
            String b = list.get(1);
            if(!map.containsKey(a)){
                map.put(a, id++);
            }
            if(!map.containsKey(b)){
                map.put(b, id++);
            }
            unionFind.union(map.get(a), map.get(b), values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);
            res[i] = -1.0;
            if(map.containsKey(a) && map.containsKey(b)){
                res[i] = unionFind.getValue(map.get(a), map.get(b));
            }
        }
        return res;
    }

    class UnionFind{
        private int[] father;
        private double[] weight;

        UnionFind(int n){
            father = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                weight[i] = 1.0;
            }
        }
        public int findF(int x){
            if(father[x] == x) return father[x];
            int o = father[x];
            father[x] = findF(father[x]);
            weight[x] *= weight[o];
            return father[x];
        }
        public void union(int a, int b, double v){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB){
                return;
            }
            father[fA] = fB;
            weight[fA] = v * weight[b] / weight[a];
        }
        public double getValue(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB){
                return weight[b] / weight[a];
            }
            return -1.0;
        }
    }
}
