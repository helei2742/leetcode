package 剑指offer.leetcode_剑指Offer59I_滑动窗口的最大值;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;

        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[len-k+1];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            while(!queue.isEmpty()&&nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.add(i);

            if(queue.peekLast() - k == queue.peek()){
                queue.poll();
            }
            if(i + 1 >= k){
                res[idx++] = nums[queue.peek()];
            }
        }
        return res;
    }

    private int[] sw(int[] nums, int k) {
        int len = nums.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->nums[b] - nums[a]);

        int l = 0, r = 0;
        int[] res = new int[len-k+1];
        int idx = 0;
        while(r < len){
            queue.add(r);
            r++;
            while(r - l > k){
                queue.remove(l);
                l++;
            }
            if(r >= k-1){
                res[idx++] = nums[queue.peek()];
            }
        }
        return res;
    }
}
