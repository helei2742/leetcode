package DFS.leetcode_386_字典序排数;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int lim;
    private List<Integer> ans;
    public List<Integer> lexicalOrder(int n) {
        lim = n;
        ans = new ArrayList<>();
        dfs(0);
        return ans;
    }
    private void dfs(int num){
        if(num > lim) return;
        ans.add(num);
        for (int i = 0; i <= 9; i++) {
            if(num!=0 && i !=0) continue;
            dfs(num*10 + i);
        }
    }
}
