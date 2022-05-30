package 动态规划.leetcode_118_杨辉三角;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);

        for (int i = 1; i <= numRows; i++) {
            List<Integer> level = new ArrayList<>();
            List<Integer> pre = res.get(i-1);

            for (int j = 0; j <= i; j++) {
                int t = 0;
                if(j-1>=0){
                    t += pre.get(j-1);
                }
                t += pre.get(j);
                level.add(t);
            }
            res.add(level);
        }
        return res;
    }
}
