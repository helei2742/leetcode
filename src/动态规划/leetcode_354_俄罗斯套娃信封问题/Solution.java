package 动态规划.leetcode_354_俄罗斯套娃信封问题;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    //记忆搜也超时呢
/*    public int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a1[1] - a2[1];
            } else {
                return a1[0] - a2[0];
            }
        });
        mem = new int[envelopes.length];
        Arrays.fill(mem, -1);

        int ans = 0;
        for (int i = 0; i < envelopes.length; i++) {
            ans  = Math.max(dfs(envelopes, i), ans);
        }
        return ans;
    }
    private int[] mem;
    private int dfs(int[][] envelopes, int start){
        if(start==envelopes.length) return 1;
        if(mem[start] != -1) return mem[start];

        int res = 0;
        for (int i = start + 1; i < envelopes.length; i++) {
            if(envelopes[start][0] < envelopes[i][0]
                    && envelopes[start][1] < envelopes[i][1]){
                res = Math.max(res, dfs(envelopes, i));
            }
        }
        return mem[start] = res+1;
    }*/

    //动归也超时
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }


}
