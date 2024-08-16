package 剑指Offer二.leetcode_剑指OfferII075_数组相对排序;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] valIdx = new int[1001];
        Arrays.fill(valIdx, -1);

        int l1 = arr1.length, l2 = arr2.length;
        for (int i = 0; i < l2; i++) {
            valIdx[arr2[i]] = i;
        }

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < l1; i++) {
            list.addLast(arr1[i]);
        }
        list.sort((a, b)->{
            if(valIdx[a] == -1) {
                return valIdx[b] == -1 ? a - b : 1;
            }else {
                return valIdx[b] == -1 ? 1 : valIdx[a] - valIdx[b];
            }
        });

        for (int i = 0; i < l1; i++) {
            arr1[i] = list.get(i);
        }
        return arr1;
    }
    private int[] fn(int[] arr1, int[] arr2) {
        int l1 = arr1.length, l2 = arr2.length;
        int maxVal = 0;
        for (int i = 0; i < l1; i++) {
            if(arr1[i] > maxVal){
                maxVal = arr1[i];
            }
        }
        int[] bucket = new int[maxVal+1];
        for (int i = 0; i < l1; i++) {
            bucket[arr1[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < l2; i++) {
            int i1 = arr2[i];
            for (int j = 0; j < bucket[i1]; j++) {
                arr1[idx++] = i1;
            }
            bucket[i1] = 0;
        }

        for (int i = 0; i <= maxVal; i++) {
            while(bucket[i]-- > 0){
                arr1[idx++] = i;
            }
        }
        return arr1;
    }

}
