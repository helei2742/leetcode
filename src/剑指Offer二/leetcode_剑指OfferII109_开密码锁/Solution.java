package 剑指Offer二.leetcode_剑指OfferII109_开密码锁;

import java.util.*;

class Solution {
    public int openLock(String[] deadends, String target) {
        if(target.equals("0000")) return 0;

        Set<String> deadSet = new HashSet<>();
        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        if(deadSet.contains("0000")) return -1;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        visited.add("0000");

        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String now = queue.poll();
                for (String next : nextStatus(now)) {
                    if(!visited.contains(next) && !deadSet.contains(next)){
                        if(next.equals(target)){
                            return res;
                        }
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }
    private List<String> nextStatus(String str){
        char[] chars = str.toCharArray();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char aChar = chars[i];
            char pre = aChar == '9' ? '0' : (char)(aChar + 1);
            chars[i] = pre;
            res.add(new String(chars));
            char next = aChar == '0' ? '9' :(char)(aChar - 1);
            chars[i] = next;
            res.add(new String(chars));
            chars[i] = aChar;
        }
        return res;
    }
}
