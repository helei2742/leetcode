package 图.leetcode_802_找到最终的安全状态;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        int[][] graph = {{1,2},{2,3},{5},{0},{5},{}, {}};
//        int[][] graph = {{},{0,2,3,4},{3},{4},{}};
        int[][] graph =
                {{1,3,4},{2,3,4},{0,3},{4},{}};
        Solution solution = new Solution();
        List<Integer> a = solution.eventualSafeNodes(graph);
        System.out.println(a);
    }
}
