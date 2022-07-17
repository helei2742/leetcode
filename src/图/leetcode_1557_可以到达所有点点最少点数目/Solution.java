package 图.leetcode_1557_可以到达所有点点最少点数目;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        int[] inDup = new int[n];

        for (List<Integer> edge : edges) {
            inDup[edge.get(1)]++;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(inDup[i] == 0) res.add(i);
        }
        return res;
    }
}