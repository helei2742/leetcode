package 剑指Offer二.leetcode_剑指OfferII032_有效的变位词;

class Solution {
    public boolean isAnagram(String s, String t) {

        int l1 = s.length(), l2 = t.length();
        if(l1 != l2 || s.equals(t)) return false;

        int[] need = new int[26], window = new int[26];

        for (int i = 0; i < l1; i++) {
            need[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < l2; i++) {
            window[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(need[i] != window[i]) return false;
        }
        return true;
    }
}
