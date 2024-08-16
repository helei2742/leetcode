package 滑动窗口.leetcode_862_和至少为K的最短子数组;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n+1];
        for (int i = 0; i < n; i++) {
            preSum[i+1] = nums[i] + preSum[i];
        }
        int res = n+1;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            long curSum = preSum[i];
            while(!deque.isEmpty() && curSum - preSum[deque.peekFirst()] >= k) {
                res = Math.min(res, i - deque.pollFirst());
            }
            while(!deque.isEmpty() && preSum[deque.peekLast()] >= curSum){
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        return res;
    }


    public int fn(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = 0, window = 0;
        int res = len;

        while(right < len) {
            window += nums[right++];

            while(left < right && window >= k) {
                window -= nums[left];
                res = Math.min(res, right - left + 1);
                left++;
            }
        }

        return res;
    }
}
