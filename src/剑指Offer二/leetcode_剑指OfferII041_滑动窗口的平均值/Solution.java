package 剑指Offer二.leetcode_剑指OfferII041_滑动窗口的平均值;

import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {
    private Queue<Integer> queue;
    private int cur;
    private int size;
    private double sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if(cur < size){
            cur++;
            sum+=val;
            queue.offer(val);
        }else {
            int h = queue.poll();
            sum = sum - h + val;
            queue.offer(val);
        }
        return sum / size;
    }
}