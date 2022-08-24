package 剑指Offer二.leetcode_剑指OfferII005_单词长度的最大乘积;

class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] mask = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            int wl = word.length();
            int m = 0;
            for (int j = 0; j < wl; j++) {
                m |= 1<<(word.charAt(j)-'a');
            }
            mask[i] = m;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if((mask[i] & mask[j]) == 0) {
                    res = Math.max(res, words[i].length()*words[j].length());
                }
            }
        }
        return res;
    }
}
