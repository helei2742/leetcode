package 并查集.leetcode_1202_交换字符集中的元素;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int size = s.length();
        UnionFind unionFind = new UnionFind(size);

        for (List<Integer> pair : pairs) {
            unionFind.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            int root = unionFind.findF(i);
            if(!map.containsKey(root)) {
                PriorityQueue<Character> q = new PriorityQueue<>();
                q.add(s.charAt(i));
                map.put(root, q);
            }else {
                map.get(root).add(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int root = unionFind.findF(i);
            sb.append(map.get(root).poll());
        }

        return sb.toString();
    }

    class UnionFind {
        private final int [] father;
        UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        private int findF(int x) {
            if(x != father[x]) {
                father[x] = findF(father[x]);
            }
            return father[x];
        }

        private void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father[fA] = fB;
        }

    }
}
