package 剑指Offer二.leetcode_剑指OfferII034_外星语言是否排序;

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[order.charAt(i)-'a'] = i;
        }

        int len = words.length;

        for (int i = 1; i < len; i++) {
            boolean f = false;
            for (int j = 0; j < words[i-1].length() && j < words[i].length(); j++) {
                int pre = count[words[i-1].charAt(j) - 'a'];
                int cur = count[words[i].charAt(j) - 'a'];

                if(pre > cur){
                    return false;
                }
                if(pre < cur){
                    f = true;
                    break;
                }
            }
            if(!f){
                if(words[i-1].length() > words[i].length()) return false;
            }
        }
        return true;
    }
}