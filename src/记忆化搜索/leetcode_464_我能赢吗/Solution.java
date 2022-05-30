package 记忆化搜索.leetcode_464_我能赢吗;

public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal) return true;
        if((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;

        return dfs(0, desiredTotal, new Boolean[1 << maxChoosableInteger], maxChoosableInteger);
    }

    //state对 n个数字哪些被旋转了进行状态压缩。 state对应二进制的每一位为1表示该位的数被选择了
    private boolean dfs(int state, int desiredTotal, Boolean [] mem, int maxChoosable){
        if(mem[state] != null) return mem[state];

        for (int i = 1; i <= maxChoosable; i++) {
            //当前数字的位
            int current = 1<<(i-1);
            //逻辑与不为1、说明i对应的那一位在state中为1，已经被选择了
            if((current & state) != 0) continue;
            if(i >= desiredTotal || !dfs(current|state, desiredTotal-i,mem,maxChoosable)){
                return mem[state] = true;
            }
        }
        return mem[state] = false;
    }
}
