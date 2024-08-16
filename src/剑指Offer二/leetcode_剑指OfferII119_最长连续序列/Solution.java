package 剑指Offer二.leetcode_剑指OfferII119_最长连续序列;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLength = 0;
        for (Integer num : numSet) {
            if(!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                maxLength = Math.max(currentLength, maxLength);
            }
        }
        return maxLength;
    }
    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        int idx = 0;
        for (int num : nums) {
            if(!idxMap.containsKey(num)){
                idxMap.put(num, idx++);
            }
        }
        UnionFound unionFound = new UnionFound(idx);
        Set<Integer> set = idxMap.keySet();
        for (Integer num : set) {
            if(set.contains(num-1)){
                unionFound.union(idxMap.get(num), idxMap.get(num-1));
            }
            if(set.contains(num+1)){
                unionFound.union(idxMap.get(num), idxMap.get(num+1));
            }
        }

        int res = 0;
        for (int i : unionFound.size) {
            res = Math.max(res, i);
        }
        return res;
    }
    class UnionFound{
        private int[] father;
        public int[] size;
        UnionFound(int n) {
            father = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public int findF(int x){
            if(x!=father[x]) father[x] = findF(father[x]);
            return father[x];
        }
        public void union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father[fA] = fB;
            size[fB] += size[fA];
        }
    }
}
