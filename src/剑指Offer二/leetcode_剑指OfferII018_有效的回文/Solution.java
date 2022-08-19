package 剑指Offer二.leetcode_剑指OfferII018_有效的回文;

class Solution {
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length()-1;

        while(l<r){
            char cL = s.charAt(l);
            char cR = s.charAt(r);
            if(!Character.isDigit(cL) && !Character.isAlphabetic(cL)){
                l++;
                continue;
            }
            if(!Character.isDigit(cR) && !Character.isAlphabetic(cR)) {
                r--;
                continue;
            }
            cL = Character.toLowerCase(cL);
            cR = Character.toLowerCase(cR);

            if(cL == cR){
                l++;
                r--;
            }else{
                return false;
            }
        }
        return l==r;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.isPalindrome("0P");
    }
}
