package 滑动窗口.leetcode_978_最长湍流子数组;

public class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        //dp[i][0] 代表以i处结尾，arr[i-1] > arr[i]的结果
        //dp[i][1] 代表以i处结尾，arr[i-1] < arr[i]的结果
        int[][] dp = new int[len][2];
        dp[0][0]=dp[0][1]=1;
        for (int i = 1; i < len; i++) {
            dp[i][0]=dp[i][1]=1;
            if(arr[i-1]>arr[i]){
                dp[i][0] = dp[i-1][1] + 1;
            }else if(arr[i-1]<arr[i]){
                dp[i][1] = dp[i-1][0] + 1;
            }
        }
        int res = 1;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i][1]);
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    /**
     *
     * @param arr
     * @return
     */
    private int sw(int[] arr){
        int len = arr.length;
        int left = 0, right = 0, res = 1;
        while(right < len-1){
            if(left == right) {
                right++;
                if(arr[left] == arr[left+1]){
                    left++;
                }
            }else {
                if(arr[right] > arr[right+1] && arr[right-1] < arr[right]) {
                    right++;
                }else if(arr[right] < arr[right+1] && arr[right-1] > arr[right]) {
                    right++;
                }else {
                    left = right;
                }
            }
            res = Math.max(right - left + 1, res);
        }
        return res;
    }
}
