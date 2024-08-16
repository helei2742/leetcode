package hot100.leetcode_448_找到所有数组中消失的数字;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - 1) % n;
            nums[idx] += n;
        }
        for (int i = 0; i < n; i++) {
            if(nums[i] <= n){
                res.add(i);
            }
        }

        return res;
    }
}
