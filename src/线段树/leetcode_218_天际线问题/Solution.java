package 线段树.leetcode_218_天际线问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;
        if (len == 0) return new ArrayList<>();
        return segment(buildings, 0, len - 1);
    }

    private List<List<Integer>> segment(int[][] buildings, int l, int r) {
        List<List<Integer>> res = new ArrayList<>();

        if(l == r) {
            res.add(Arrays.asList(buildings[l][0], buildings[l][2]));
            res.add(Arrays.asList(buildings[l][1], 0));
            return res;
        }

        int mid = (l + r)/2;
        List<List<Integer>> leftRes = segment(buildings, l, mid);
        List<List<Integer>> rightRes = segment(buildings, mid + 1, r);

        int leftIndex = 0, rightIndex = 0;
        int leftCurrentHeight = 0, rightCurrentHeight = 0;
        int leftX, leftY, rightX, rightY;

        while (leftIndex < leftRes.size() || rightIndex < rightRes.size()) {
            if(leftIndex >= leftRes.size()) {
                res.add(rightRes.get(rightIndex++));
            }else if(rightIndex >= rightRes.size()) {
                res.add(leftRes.get(leftIndex++));
            }else {
                leftX = leftRes.get(leftIndex).get(0);
                leftY = leftRes.get(leftIndex).get(1);

                rightX = rightRes.get(rightIndex).get(0);
                rightY = rightRes.get(rightIndex).get(1);

            }
        }
        return new ArrayList<>();
    }
}
