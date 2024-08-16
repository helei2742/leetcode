package hot100.leetcode_347_前K个高频元素;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> fMap = new HashMap<>();

        for (int num : nums) {
            fMap.put(num, fMap.getOrDefault(num, 0)+1);
        }
        int[][] arr = new int[fMap.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : fMap.entrySet()) {
            arr[idx][0] = entry.getKey();
            arr[idx++][1] = entry.getValue();
        }

        this.k = k;

        fSort(arr, 0, arr.length-1);
        return res;

    }
    private int k;
    private int[] res;
    private void fSort(int[][] arr, int left, int right) {
        int l = left, r = right;
        int mid = (left+right) >> 1;

        while(l <= r) {
            while(arr[l][1] > arr[mid][1]) {
                l++;
            }
            while(arr[r][1] < arr[mid][1]) {
                r--;
            }

            if(l <= r){
                int[] t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                l++;
                r--;
            }
        }
        // left r l right
        if(k <= r){
            fSort(arr, left, r);
        }else if(k >= l){
            fSort(arr, l, right);
        }else {
            res = new int[k];
            System.out.println(mid);
            System.out.println(Arrays.deepToString(arr));
            for (int i = 0; i < k; i++) {
                res[i] = arr[i][0];
            }
        }
    }
}
