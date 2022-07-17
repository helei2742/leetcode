package 剑指offer.leetcode_剑指Offer58I_翻转单词顺序;

import java.util.LinkedList;

class Solution {
    public String reverseWords(String s) {
        if(s.length() == 0) return "";
        int l = 0, r = s.length() - 1;

        LinkedList<String> resr = new LinkedList<>();
        LinkedList<String> resl = new LinkedList<>();
        while (l<r){
            StringBuilder sbl = new StringBuilder();
            StringBuilder sbr = new StringBuilder();
            while (l<r&& s.charAt(l) != ' '){
                sbl.append(s.charAt(l));
                l++;
            }
            while (l<r&& s.charAt(r) != ' '){
                sbr.append(s.charAt(r));
                r--;
            }
            l++;
            r--;
            if(sbr.length()>0)
                resl.addLast(sbr.reverse().toString());
            if(sbl.length()>0)
                resr.addFirst(sbl.toString());
        }

        StringBuilder sb = new StringBuilder();
        for (String s1 : resl) {
            sb.append(s1).append(" ");
        }
        if(sb.length()>=0)
            sb.deleteCharAt(sb.length()-1);
        for (String s1 : resr) {
            sb.append(" ").append(s1);
        }
        String string = sb.toString();
        while (string.startsWith(" "))
            string = string.substring(1);
        return string;
    }

}
