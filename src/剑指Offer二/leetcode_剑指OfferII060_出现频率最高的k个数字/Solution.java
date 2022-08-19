package 剑指Offer二.leetcode_剑指OfferII060_出现频率最高的k个数字;

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1]-a[1]);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[] {entry.getKey(), entry.getValue()});
        }
        int [] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }
        return res;
    }
}