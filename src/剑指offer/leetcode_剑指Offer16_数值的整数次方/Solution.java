package 剑指offer.leetcode_剑指Offer16_数值的整数次方;

public class Solution {
    public double myPow(double x, int n) {
        return n>=0?quickMux(x,n):1.0/quickMux(x,n);
    }

    public double quickMux(double x, int n){
        if(n == 0) return 1.0;
        double v = quickMux(x, n / 2);
        return n%2==0?v*v:v*v*x;
    }
}
