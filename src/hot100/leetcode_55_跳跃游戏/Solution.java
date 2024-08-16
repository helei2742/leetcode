package hot100.leetcode_55_跳跃游戏;

class Solution {
    public boolean canJump(int[] nums) {

/*        int len = nums.length;

        //dp[i]表示，能不从1挑到i位置
        //dp[i] |=( dp[j] && nums[j] >= i-j ) 0<= j <i
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if(dp[j] && nums[j] >= i-j){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len-1];
        */

        int canGo = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > canGo) return false;
            canGo = Math.max(canGo, i + nums[i]);
        }
        return true;
    }
}
