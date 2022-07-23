package 剑指offer.leetcode_剑指Offer66_构建乘积数组;

public class Solution {
    public int[] constructArr(int[] a) {
        int len = a.length;
        if(len<=0) return new int[0];
        int[] prefixMul = new int[len];
        int[] postfixMul = new int[len];

        prefixMul[0] = 1;
        for (int i = 1; i < len; i++) {
            prefixMul[i] = a[i-1] * prefixMul[i-1];
        }
        postfixMul[len-1] = 1;
        for (int i = len-2; i >= 0; i--) {
            postfixMul[i] = a[i+1] * postfixMul[i+1];
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = prefixMul[i] * postfixMul[i];
        }
        return res;
    }
}
