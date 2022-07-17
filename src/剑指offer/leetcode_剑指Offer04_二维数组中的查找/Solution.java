package 剑指offer.leetcode_剑指Offer04_二维数组中的查找;

import java.util.Arrays;

class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length==0) return false;
        int m = matrix.length;

        int n = matrix[0].length;

        int[] firstCol = new int[m];
        for (int i = 0; i < m; i++) {
            firstCol[i] = matrix[i][0];
        }

        int index = bSearch(firstCol, target);
        if(index < m && firstCol[index] == target){
            return true;
        }else {
            for (int i = 0; i < index; i++) {
                int bSearch = bSearch(matrix[i], target);
                if(bSearch < n && matrix[i][bSearch] == target) return true;
            }
        }
        return false;
    }

    private int bSearch(int[] nums, int target){
        int l = 0, r = nums.length-1;
        int mid = 0;
        while (l<=r){
            mid = (l+r)/2;
            if(nums[mid] >= target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return l;
    }
}