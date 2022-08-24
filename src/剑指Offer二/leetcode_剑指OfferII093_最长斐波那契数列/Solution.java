package 剑指Offer二.leetcode_剑指OfferII093_最长斐波那契数列;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }
        int[][] dp = new int[len][len];

        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = map.getOrDefault(arr[i] - arr[j], -1);
                if(k >= 0){
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                res = Math.max(res, dp[j][i]);
            }
        }
        return res;
    }
}
