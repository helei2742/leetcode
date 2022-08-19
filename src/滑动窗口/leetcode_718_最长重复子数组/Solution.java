package 滑动窗口.leetcode_718_最长重复子数组;


class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int [][] dp = new int[m+1][n+1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    private int sw(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            int len = Math.min(m-i, n);
            int t = maxLen(nums1, i, nums2, 0, len);
            res = Math.max(t, res);
        }
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n-i);
            int t = maxLen(nums1, 0, nums2, i, len);
            res = Math.max(t, res);
        }
        return res;
    }

    private int maxLen(int[] A, int aStart, int[] B, int bStart, int len){
        int res = 0, count = 0;
        for (int i = 0; i < len; i++) {
            if(A[aStart+i] == B[bStart+i]) {
                count++;
            }else {
                count = 0;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
