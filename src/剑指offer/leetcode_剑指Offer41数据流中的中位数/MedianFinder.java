package 剑指offer.leetcode_剑指Offer41数据流中的中位数;

import java.util.PriorityQueue;

public class MedianFinder {

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>();
        minHeap = new PriorityQueue<>((a,b)->b-a);
    }

    public void addNum(int num) {
        if(maxHeap.size()==minHeap.size()){
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }else {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    public double findMedian() {
        return maxHeap.size()==minHeap.size()?(maxHeap.peek()+minHeap.peek())/2.0:maxHeap.peek();
    }
}
