package 图.leetcode_1494_并行课程;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {

        //pre[i]记录，当前i课程到签到课，采用状态压缩，二进制第j位代表j课程是否选择。
        int[] pre = new int[n];
        for (int[] relation : relations) {
            pre[relation[1]-1] |= 1 << (relation[0]-1);
        }
        //总状态数
        int totalState = 1<<n;

        int[] dp = new int[totalState];
        Arrays.fill(dp, n);
        //0个课程需要选，所以需要学期为0
        dp[0] = 0;

        //计算二进制位中1第个数，右移以为，oneCount[]中为除去末尾1的个数，再加上末尾是否为1
        int[] oneCount = new int[totalState];
        for (int i = 0; i < totalState; i++) {
            oneCount[i] = oneCount[i>>1] + (i&1);
        }

        //枚举所有的状态token为已经上过的课程。
        for (int token = 0; token < totalState; token++) {
            if(dp[token] > n) continue;
            //记录当前哪些课是可供选择
            int current = 0;
            //枚举所有课程，
            // 找到没有上过的课程(token & (1<<j)) == 0，
            // 同时该课程的前置课程已经上过了(pre[j] & token) == pre[j]
            for (int j = 0; j < n; j++) {
                if((token & (1<<j)) == 0 && (pre[j] & token) == pre[j]){
                    current |= 1<<j;
                }
            }
            //由于得到的可选择课程集合里，可以任意组合，需依次遍历完所有子集
            for (int subMask = current; subMask > 0; subMask = subMask-1 & current) {
                if(oneCount[subMask] <= k){
                    dp[token|subMask] = Math.min(dp[token|subMask], dp[token]+1);
                }
            }
        }
        return dp[totalState-1];
    }
}