package 动态规划.leetcode_467_环绕字符串的唯一子字符串;
class Solution {


    public int findSubstringInWraproundString(String p) {
        int[] records = new int[26];
        int last = -1, max = 0;
        char[] cs = p.toCharArray();
        for (char c : cs) {
            int cur = c - 'a';
            max = (cur == last + 1) || (last == 25 && cur == 0) ? max + 1 : 1;
            if (max > records[cur]) {
                records[cur] = max;
            }
            last = cur;
        }
        int ans = 0;
        for (int num : records) {
            ans += num;
        }
        return ans;
    }
}