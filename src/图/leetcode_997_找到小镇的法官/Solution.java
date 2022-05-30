package 图.leetcode_997_找到小镇的法官;

public class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] inDup = new int[n], outDup = new int[n];

        for (int[] ints : trust) {
            inDup[ints[1]-1]++;
            outDup[ints[0]-1]++;
        }

        for (int i = 0; i < n; i++) {
            if(inDup[i] == n-1 && outDup[i] == 0)
                return i+1;
        }
        return -1;
    }
}
