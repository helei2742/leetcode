package 动态规划.leetcode_368_最大整除子集;

import java.util.*;

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = null;
        int n = nums.length;
        Arrays.sort(nums);

        //dp[i]表示以i结尾的最大整数集
        ArrayList<Integer>[] dp = new ArrayList[nums.length];

        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
            int maxSize = 0;
            int position = -1;
            for (int j = 0; j < i; j++) {
                if(nums[i]%nums[j] == 0 && dp[j].size() > maxSize){
                    //记录i前面的最大子集位置
                    position = j;
                    maxSize = dp[j].size();
                }
            }

            if(position != -1) {
                dp[i].addAll(dp[position]);
            }
            dp[i].add(nums[i]);
            if(res == null || res.size() < dp[i].size()){
                res = dp[i];
            }
        }
        return res;

/*        Arrays.sort(nums);

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= nums.length-1; i++) {
            List<Integer> dfs = dfs(nums, i);

            if(dfs.size()>res.size()){
                res = dfs;
            }
        }

        return res;*/
    }

    //搜索超时
    private List<Integer> dfs(int[] nums, int end){
        if(end == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(nums[end]);
            return list;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = end-1; i >=0; i--) {
            if(nums[end]%nums[i] != 0) continue;
            List<Integer> dfs = dfs(nums, i);
            if(dfs.size() > res.size()){
                res = dfs;
            }
        }
        res.add(nums[end]);
        return res;
    }
}