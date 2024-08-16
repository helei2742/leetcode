package 剑指Offer二.leetcode_剑指OfferII108_单词演变;

import javax.management.Query;
import java.util.*;

class Solution {
    private final int INF = Integer.MAX_VALUE-100000;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : wordList) {
            List<String> v = new ArrayList<>();
            for (String s1 : wordList) {
                if(s1.equals(s)) continue;
                if(isConnect(s, s1)) v.add(s1);
            }
            map.put(s, v);
        }
        Map<String, Integer> dis = new HashMap<>();
        Map<String, Boolean> isVisited = new HashMap<>();

        Queue<String> queue = new LinkedList<>();

        for (String s : wordList) {
            if(isConnect(s, beginWord)){
                queue.add(s);
                dis.put(s, 1);
                isVisited.put(s, true);
            }
        }
        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String now = queue.poll();
                for (String next : map.get(now)) {
                    if(next.equals(endWord)){
                        return res;
                    }
                    if(!isVisited.getOrDefault(next, false)
                            && dis.get(now) + 1 < dis.getOrDefault(next, INF)){
                        isVisited.put(next, true);
                        dis.put(next, dis.get(now) + 1);
                        queue.offer(next);
                    }
                }
            }
        }
//        System.out.println(dis);
        return dis.getOrDefault(endWord, 0);
    }

    private boolean isConnect(String a, String b){
        if(a.length() != b.length()) return false;
        int c = 0;
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)){
                c++;
                if(c == 2) return false;
            }
        }
        return true;
    }
}
