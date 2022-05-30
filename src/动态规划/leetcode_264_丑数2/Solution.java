package 动态规划.leetcode_264_丑数2;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        int [] arr = {2,3,5};
        pq.add(1L);
        set.add(1L);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long poll = pq.poll();
            ans = poll;
            for (int num : arr) {
                long next = num * poll;
                if(!set.contains(next)){
                    set.add(next);
                    pq.add(next);
                }
            }
        }
        return (int)ans;
    }
}
