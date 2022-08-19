package 剑指Offer二.leetcode_剑指OfferII019_最多删除一个字符得到回文;

class Solution {
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length();

        while(l < r){
            if(s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }else {
                boolean f = valid(s, l, r - 1) | valid(s, l+1, r);
                if(f) return f;
            }
        }
        return false;
    }
    private boolean valid(String s, int l, int r){
        while(l < r){
            if(s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }else {
                return false;
            }
        }
        return true;
    }
}