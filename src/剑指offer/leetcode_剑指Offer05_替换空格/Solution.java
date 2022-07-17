package 剑指offer.leetcode_剑指Offer05_替换空格;

class Solution {
    public String replaceSpace(String s) {
        String replace = s.replace(" ", "%20");
        return replace;
    }
}
