package 剑指offer.leetcode_剑指Offer62_圆圈中最后剩下的数字;

public class Solution {
    public int lastRemaining(int n, int m) {
      /*  boolean[] v = new boolean[n];
        int count = n;
        int t = 0;
        while (count>1){
            for (int i = 0; i < n; i++) {
                if(!v[i]){
                    if(++t==m){
                        t=0;
                        v[i] = true;
                        count--;
                        if(count==1) break;
                    }
                }
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            if(!v[i]) res=i;
        }
        return res;*/

        return fn(n,m);
    }

    private int fn(int n, int m){
        if(n==1) return 0;
        int x = fn(n-1, m);
        return ((m-1)%n + x + 1)%n;
    }
}
