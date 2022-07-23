package 剑指offer.leetcode_剑指Offer15_二进制中1的个数;

public class Solution {

    public int hammingWeight(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }
}
