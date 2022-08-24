package 剑指Offer二.leetcode_剑指OfferII072_求平方根;

class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x;
        int res = -1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if((long)mid*mid > x){
                r = mid - 1;
            }else {
                res = mid;
                l = mid + 1;
            }
        }
        return res;
    }
}
