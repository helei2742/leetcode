package 剑指offer.leetcode_剑指Offer50第一个只出现一次的字符;

class Solution {
    public char firstUniqChar(String s) {
        int[] mem = new int[26];
        for (int i = 0; i < s.length(); i++) {
            mem[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(mem[s.charAt(i)-'a'] == 1) return s.charAt(i);
        }
        return ' ';
    }
}
