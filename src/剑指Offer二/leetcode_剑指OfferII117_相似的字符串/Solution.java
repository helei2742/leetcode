package 剑指Offer二.leetcode_剑指OfferII117_相似的字符串;

import java.util.*;

class Solution {
    public int numSimilarGroups2(String[] strs) {
        List<List<Integer>> graph = new ArrayList<>();
        Map<String, Integer> idxMap = new HashMap<>();
        int idx = 0;
        for (String str : strs) {
            if(!idxMap.containsKey(str)){
                idxMap.put(str, idx++);
                graph.add(new ArrayList<>());
            }
        }

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length; j++) {
                if(i == j) continue;
                String w1 = strs[i], w2 = strs[j];
                if(isSame(w1, w2)){
                    graph.get(idxMap.get(w1)).add(idxMap.get(w2));
                }
            }
        }

        System.out.println(graph);
        int count = 0;
        boolean[] isVisited = new boolean[idx];

        for (int i = 0; i < idx; i++) {
            if(!isVisited[i]){
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                isVisited[i] = true;
                while (!queue.isEmpty()){
                    Integer now = queue.poll();
                    for (Integer next : graph.get(now)) {
                        if(!isVisited[next]){
                            queue.add(next);
                            isVisited[next] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    public int numSimilarGroups(String[] strs) {
        Map<String, Integer> idxMap = new HashMap<>();
        int idx = 0;
        for (String str : strs) {
            if(!idxMap.containsKey(str)){
                idxMap.put(str, idx++);
            }
        }
        UnionFound unionFound = new UnionFound(idx);

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length; j++) {
                if(i == j) continue;
                String w1 = strs[i], w2 = strs[j];
                if(isSame(w1, w2)){
                    unionFound.union(idxMap.get(w1), idxMap.get(w2));
                }
            }
        }
        return unionFound.count;
    }
    class UnionFound{
        private int[] father;
        public int count;
        UnionFound(int n) {
            father = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x) {
            if(father[x] != x){
                father[x] = findF(father[x]);
            }
            return father[x];
        }
        public void union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father[fA] = fB;
            count--;
        }
    }
    private boolean isSame(String s1, String s2){
        int l1 = s1.length(), l2 = s2.length();
        if(l1!=l2) return false;
        int c = 0;
        for (int i = 0; i < l1; i++) {
            if(s1.charAt(i) != s2.charAt(i)){
                c++;
            }
        }
        return c==2;
    }
}
