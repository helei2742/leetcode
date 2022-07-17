package 剑指offer.leetcode_剑指Offer64求12n;

public class Solution {
    public int sumNums(int n) {
        boolean f = n>0&&(n+=sumNums(n-1) ) > 0;
        return n;
    }
}
