package hot100.leetcode_240_搜索二维矩阵II;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int col, row;
        if(matrix == null || (row=matrix.length) == 0 || (col=matrix[0].length) == 0) {
            return false;
        }

        int x = 0, y = col-1;

        while(x < row && y >= 0){
            if(matrix[x][y] == target) {
                return true;
            } else if(matrix[x][y] > target){
                y--;
            }else {
                x++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,23,33,44,55,66};
        int i = solution.bSearch(nums, 23);
        System.out.println(i);
    }

    public int bSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int res = 0;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(nums[mid] >= target) {
                res = mid;
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return res;
    }
}
