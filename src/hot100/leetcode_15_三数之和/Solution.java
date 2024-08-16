package hot100.leetcode_15_三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            int l = i + 1, r = len - 1;
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    while (l < len - 1 && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (r > 0 && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum_2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            if (l >= nums.length) continue;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    while (l < nums.length - 1 && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (r > 0 && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                }
            }
        }
        return res;
    }
}
