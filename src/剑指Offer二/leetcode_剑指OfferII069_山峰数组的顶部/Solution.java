package 剑指Offer二.leetcode_剑指OfferII069_山峰数组的顶部;

class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length -1;
        int res = arr.length;

        while(l <= r) {
            int mid = (r-l)/2 + l;
            if(arr[mid] > arr[mid+1]){
                r = mid-1;
                res = mid;
            }else {
                l = mid+1;
            }
        }
        return res;
    }
}
