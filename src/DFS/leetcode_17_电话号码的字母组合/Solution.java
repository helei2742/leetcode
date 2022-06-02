package DFS.leetcode_17_电话号码的字母组合;

import java.util.*;


public class Solution {
    private final Character [][] chars = {{'a','b','c'},
            {'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},
            {'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
    };

    private String str;


    public List<String> letterCombinations(String digits) {
        if("".equals(digits)){
            return new ArrayList<>();
        }
        str = digits;

        return DFS(0, "");
    }

    private List<String> DFS(int index, String s) {
        if(index >= str.length()){
            List<String> list =  new ArrayList<String>();
            list.add(s);
            return list;
        }
        int n = str.charAt(index) - '0';


        ArrayList<String> res = new ArrayList<>();

        for (char c :chars[n]) {
            if(c>='a'&&c<='z')
            res.addAll(DFS(index+1, s+c));
        }
        return res;
    }
}
