package 剑指Offer二.leetcode_剑指OfferII031_最近最少使用缓存;

import java.util.*;

class LRUCache {

    private int capacity = 0;
    private Map<Integer, Integer> map;
    private PriorityQueue<int[]> pq;
    private int count = 0;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.pq = new PriorityQueue<>(capacity,(a,b)->{
            return a[0]-b[0];
        });
    }

    public int get(int key) {
        if(map.containsKey(key)){
            updatePQ(key);
            return map.get(key);
        }
        return -1;
    }

    private void updatePQ(int key) {
        count++;
        int []a = null;
        for (int[] arr : pq) {
            if(arr[1] == key) {
                a = arr;
                break;
            }
        }
        pq.remove(a);
        a[0] = count;
        pq.add(a);
    }

    public void put(int key, int value) {
        count++;
        if(map.containsKey(key)){
            updatePQ(key);
            map.put(key, value);
        }else {
            if(map.size() == this.capacity){
                int[] poll = pq.poll();
                map.remove(poll[1]);
                map.put(key, value);
                poll[0] = count;
                poll[1] = key;
                pq.add(poll);
            }else {
                pq.add(new int[]{count, key});
                map.put(key, value);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        lruCache.put(4,4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}