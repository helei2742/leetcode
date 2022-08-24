package 剑指Offer二.leetccde_剑指OfferII073_狒狒吃香蕉;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int i = 0; i < piles.length; i++) {
            r = Math.max(piles[i],r);
        }
        int res = 0;
        while(l <= r){
            int mid = l + (r-l)/2;
            long t = getTime(piles, mid);
            if(t <= h){
                r = mid - 1;
                res = mid;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }
    private long getTime(int[] piles, int speed) {
        long time = 0;
        for (int pile : piles) {
            int t = (pile+speed-1)/speed;
            time += t;
        }
        return time;
    }
}
