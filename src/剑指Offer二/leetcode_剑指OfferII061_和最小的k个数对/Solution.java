package 剑指Offer二.leetcode_剑指OfferII061_和最小的k个数对;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a,b)->(a.get(0)+a.get(1))-(b.get(0)+b.get(1)));

        int l1 = nums1.length;
        int l2 = nums2.length;

        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                List<Integer> list = new ArrayList<>(2);
                list.add(nums1[i]);
                list.add(nums2[j]);
                pq.add(list);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            res.add(pq.poll());
        }
        return res;
    }
}
