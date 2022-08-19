package 滑动窗口.leetcode_424_替换后的最长重复字符;

public class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int left = 0;
        int right = 0;
        int[] nums = new int[26];
        int max = 0;
        for (right = 0; right < len; right++) {
            int rIdx = s.charAt(right) - 'A';
            nums[rIdx]++;
            max = Math.max(max, nums[rIdx]);
            while (right-left+1>max+k){
                nums[s.charAt(left)-'A']--;
                left++;
            }
        }
        return right - left;
    }
}
