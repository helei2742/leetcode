package 剑指offer.leetcode_面试题40最小的k个数;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length<=k){
            return arr;
        }
        ans = new int[k];
        this.k = k-1;
        if(this.k<0) return new int[0];
        quickSort(arr, 0, arr.length-1);
        return ans;
    }
    private int k;
    private int[] ans;
    private void quickSort(int[] arr, int left, int right){
        int l = left, r = right, flag = arr[(l+r)/2];

        while (l<=r){
            while (arr[l]<flag){
                l++;
            }
            while (arr[r]>flag){
                r--;
            }
            if(l<=r){
                int t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                l++;
                r--;
            }
        }
        //left    r  l  right
        if(k<=r){
            quickSort(arr, left, r);
        }else if(k>=l){
            quickSort(arr, l, right);
        }else {
            for (int i = 0; i <=r+1; i++) {
                ans[i] = arr[i];
            }
        }
    }
}
