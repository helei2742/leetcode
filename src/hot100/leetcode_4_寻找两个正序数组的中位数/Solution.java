package hot100.leetcode_4_寻找两个正序数组的中位数;

import java.util.Arrays;

/**
 *给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int[] arr = new int[l1+l2];
        int idx = 0;
        int p1 = 0, p2 = 0;
        while(p1<l1 && p2<l2){
            if(nums1[p1] < nums2[p2]){
                arr[idx++] = nums1[p1++];
            }
            else {
                arr[idx++] = nums1[p2++];
            }
        }
        while(p1 < l1){
            arr[idx++] = nums1[p1++];
        }
        while(p2 < l2){
            arr[idx++] = nums1[p2++];
        }
        if(idx %2 == 1){
            return arr[idx/2 +1];
        }
        else {
            return (arr[idx/2]+arr[idx/2+1])/2.0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = new int[]{1,3};
        int[] arr2 = new int[]{2};
        solution.findMedianSortedArrays(arr1, arr2);
    }
}
