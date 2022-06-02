package DFS.leetcode_565_数组嵌套;

public class Solution {
    public int arrayNesting(int[] nums) {
        isVisited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, 1);
        }
        return ans;
    }
    private boolean [] isVisited;
    private int ans;
    private void dfs(int[] nums, int index, int len) {
        if(isVisited[index]) return;
        isVisited[index] = true;
        ans = Math.max(ans, len);
        dfs(nums, nums[index], len + 1);
    }

    private int noDfs(int[] nums){
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != -1){
                int start = nums[i], count = 0;
                while(nums[start] != -1){
                    int t = start;
                    start = nums[start];
                    nums[t] = -1;
                    count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
