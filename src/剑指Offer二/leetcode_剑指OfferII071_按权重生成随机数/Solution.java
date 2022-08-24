package 剑指Offer二.leetcode_剑指OfferII071_按权重生成随机数;

import java.util.Random;

class Solution {
    private int[] preSum;
    private int total;
    public Solution(int[] w) {
        int len = w.length;
        preSum = new int[len];
        preSum[0] = w[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = w[i] + preSum[i-1];
        }
        total = preSum[len-1];
    }

    public int pickIndex() {
        int num = (int) (Math.random() * total + 1);

        int l = 0, r = preSum.length - 1;
        while(l<r) {
            int mid = l + (r-l)/2;
            if(preSum[mid] >= num){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
}
