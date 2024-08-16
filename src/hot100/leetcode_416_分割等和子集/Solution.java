package hot100.leetcode_416_分割等和子集;

class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length, sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if(sum % 2 != 0) return false;

        int target = sum/2;

        if(max > target) return false;
        boolean[][] dp = new boolean[len][target+1];
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= target; j++) {
                if(j>=nums[i]) {
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len-1][target];
    }
}
