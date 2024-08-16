package hot100.leetcode_238_除自身以外数组的乘积;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] preMul = new int[len];
        preMul[0] = 1;
        for (int i = 1; i < len; i++) {
            preMul[i] = preMul[i-1] * nums[i-1];
        }
        int[] postMul = new int[len];
        postMul[len-1] = 1;
        for (int i = len-2; i >= 0; i--) {
            postMul[i] = postMul[i+1] * nums[i-1];
        }

        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            res[i] = preMul[i] * postMul[i];
        }

        return res;
    }
}
