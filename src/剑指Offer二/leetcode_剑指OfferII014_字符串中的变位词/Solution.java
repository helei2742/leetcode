package 剑指Offer二.leetcode_剑指OfferII014_字符串中的变位词;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] need = new int[26], window = new int[26];
        int needCount = 0;
        int l1 = s1.length(), l2 = s2.length();
        for (int i = 0; i < l1; i++) {
            int idx = s1.charAt(i) - 'a';
            if(need[idx]++ == 0) needCount++;
        }

        int left = 0, right = 0;
        int count = 0;
        while(right < l2) {
            int rIdx = s2.charAt(right) - 'a';
            window[rIdx]++;
            if(window[rIdx] == need[rIdx]) count++;
            right++;

            while(right-left>=l1){
                if(count==needCount) return true;
                int lIdx = s2.charAt(left) - 'a';
                left++;
                if(window[lIdx] == need[lIdx]) count--;
                window[lIdx]--;
            }
        }
        return false;
    }
}