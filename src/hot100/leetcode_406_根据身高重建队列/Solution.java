package hot100.leetcode_406_根据身高重建队列;

import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b)->{if(a[0] == b[0]) return a[1] - b[1];else return b[0]-a[0];});

        LinkedList<int[]> res = new LinkedList<>();

        for (int[] person : people) {
            if(person[1] >= res.size()){
                res.add(person);
            }else {
                res.add(person[1], person);
            }
        }
        System.out.println(res);

        return res.toArray(new int[res.size()][]);
    }
}
