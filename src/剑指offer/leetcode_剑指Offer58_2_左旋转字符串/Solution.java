package 剑指offer.leetcode_剑指Offer58_2_左旋转字符串;

    class Solution {
        public String reverseLeftWords(String s, int n) {
            int len = s.length();
            StringBuilder sb = new StringBuilder();

            for (int i = n; i < len; i++) {
                sb.append(s.charAt(i));
            }
            for (int i = 0; i < n; i++) {
                sb.append(s.charAt(i));
            }
            return sb.toString();

    /*        String s1 = s.substring(0, n);
            String s2 = s.substring(n + 1, s.length());
            return s2+s1;*/
        }
    }