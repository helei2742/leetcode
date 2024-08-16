package hot100.leetcode_11_盛最多水的容器;

class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        int l = 0, r = len-1, res = 0;
        while(l<r){
            res = height[l]<height[r]?Math.max(res, (r-l)*height[l++]):Math.max(res, (r-l)*height[r--]);
        }
        return res;
    }
}
