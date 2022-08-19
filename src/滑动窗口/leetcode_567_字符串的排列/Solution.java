package 滑动窗口.leetcode_567_字符串的排列;


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] need = new int[26], window = new int[26];
        int needCount = 0;
        int m = s1.length();
        int n = s2.length();
        for (int i = 0; i < m; i++) {
            int t = s1.charAt(i) - 'a';
            if(need[t] == 0) needCount++;
            need[t]++;
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right<n){
            int rIdx = s2.charAt(right) - 'a';
            right++;

            window[rIdx]++;
            if(window[rIdx] == need[rIdx]){
                valid++;
            }

            while (right-left>=m){
                if(valid == needCount) return true;

                int lIdx = s2.charAt(left) - 'a';
                left++;
                if(window[lIdx] == need[lIdx])
                    valid--;
                window[lIdx]--;
            }
        }
        return false;
    }
}