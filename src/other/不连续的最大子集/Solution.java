package other.不连续的最大子集;

import java.util.Arrays;

public class Solution {
    private static int func(int[] arr){
        int len = arr.length;

        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if(Math.abs(arr[i]-arr[j])>1)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,5,6,7};
        System.out.println(func(arr));
    }
}
