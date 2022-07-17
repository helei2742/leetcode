package 剑指offer.leetcode_剑指Offer48最长不含重复字符的子字符串;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
       return dp1(s);
    }
    private int dp1(String s){
        int n = s.length();
        if(n<=0) return 0;
        //dp[i]代表，从0开始包含i位置，的最大不重复子字符串的长度。
        //还需要记录当前位置字符的上一次出现位置。计为j
        //若i-j大于dp[i-1]说明i位置的字符不在dp[i-1]的范围里,取最小的，则dp[i] = dp[i-1]+1
        //若i-j小于dp[i-1]说明i位置的字符在dp[i-1]的范围里,取最小的，则dp[i] = i-j
        int[] dp = new int[n];
        Map<Character, Integer> lastIdxMap = new HashMap<>(n);
        dp[0] = 1;
        lastIdxMap.put(s.charAt(0), 0);
        int res = 1;
        for (int i = 1; i < n; i++) {
            Integer last = lastIdxMap.getOrDefault(s.charAt(i), -1);
            if(i - last > dp[i-1]){
                dp[i] = dp[i-1] + 1;
            }else {
                dp[i] = i - last;
            }
            res = Math.max(res, dp[i]);
            lastIdxMap.put(s.charAt(i), i);
        }
        return res;
    }

    private int dp2(String s){
        int n = s.length();
        Map<Character, Integer> lastIdxMap = new HashMap<>(n);
        int res = 0, t = 0;
        for (int i = 0; i < n; i++) {
            Integer last = lastIdxMap.getOrDefault(s.charAt(i), -1);
            if(i-last>t){
                t = t+1;
            }else {
                t = i - last;
            }
            res = Math.max(res, t);
            lastIdxMap.put(s.charAt(i), i);
        }
        return res;
    }

    /**
     * 本质类似于动态规划，动态规划中，每次判断当前字符上一次出现位置是否在上一个位置的不重复串的范围内从而更新当前位置的值
     *                              实际上就是对上一次出现对位置last和 上一次确定的位置进行比较大小，大的就是左指针
     * @param s
     * @return
     */
    private int twoPoint(String s){
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for(int j = 0; j < s.length(); j++) {
            if(dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }
}
