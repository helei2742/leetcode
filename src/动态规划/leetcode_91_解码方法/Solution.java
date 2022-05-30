package 动态规划.leetcode_91_解码方法;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    public int numDecodings(String s) {
/*        mem = new int[s.length()+1];
        Arrays.fill(mem, -1);
        return dfs(s, 0);*/
        return fnDP(s);
    }

    //mem[i]表示到i位置结尾的字串的解码方法的和
    private int[] mem;
    //超时呢
    //加记忆
    //
    private int dfs(String str, int index){
        if(mem[index]!=-1) return mem[index];

        if(str.length()>index&&str.charAt(index) == '0') return 0;
        if(index >= str.length() - 1) return 1;

        //一位数解码的时候一定符合条件拉
        int count = dfs(str, index+1);

        int encode = Integer.parseInt(str.substring(index,index+2));

        if(encode>=1&&encode<=26) {
            count += dfs(str, index+2);
        }
        return mem[index]=count;
    }

    //不够快，直接dp
    private int fnDP(String s){
        int[] dp = new int[s.length()+1];

        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            if(s.charAt(i-1) != '0')
                dp[i] = dp[i-1];
            if(i > 1
                    && s.charAt(i - 2) != '0'
                    && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)){
                dp[i] += dp[i-2];
            }

        }
        return dp[s.length()];
    }

}