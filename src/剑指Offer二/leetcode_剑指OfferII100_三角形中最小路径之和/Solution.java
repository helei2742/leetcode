package 剑指Offer二.leetcode_剑指OfferII100_三角形中最小路径之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<int[]> dp = new ArrayList<>();

        dp.add(new int[]{triangle.get(0).get(0)});

        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> level = triangle.get(i);
            int size = level.size();
            int[] t = new int[size];
            t[0] = dp.get(i-1)[0] + level.get(0);
            t[size-1] = dp.get(i-1)[size-2] + level.get(size-1);
            for (int i1 = 1; i1 < size -1; i1++) {
                t[i1] = Math.min(dp.get(i-1)[i1 -1], dp.get(i-1)[i1])+ level.get(i1);
            }

            dp.add(t);
        }
        int res = Integer.MAX_VALUE;

        int[] ints = dp.get(dp.size() - 1);
        for (int anInt : ints) {
            res = Math.min(anInt, res);
        }
        return res;
    }
}
