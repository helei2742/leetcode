import 动态规划.Qs;
import 动态规划.leetcode_剑指offer_最长公共zixulie.Solution;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
/*        dijekesitra d = new dijekesitra(4);


        d.add(1,2,2);
        d.add(2,3,2);
        d.add(2,4,1);
        d.add(1,3,5);
        d.add(3,4,3);
        d.add(1,4,4);
        d.dijkstra(1);*/
//
//        Solution solution = new Solution();
//        solution.LCS("abcbdab", "bdcaba");

        Qs qs = new Qs();

        int[] t = {123,231,232,22222,1,2312,2};
        qs.qs(t,0,t.length-1);
        System.out.println(Arrays.toString(t));
    }
}
