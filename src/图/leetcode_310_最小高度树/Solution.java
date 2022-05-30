package 图.leetcode_310_最小高度树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    int N = 20010, index = 0;
    int[] head = new int[N], edge = new int[N*2], next = new int[N*2];
    int[] maxDownHeight = new int[N], secondDownHeight = new int[N],maxUpHeight = new int[N],p = new int[N];

    void add(int a, int b) {
        edge[index] = b;
        next[index] = head[a];
        head[a] = index++;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Arrays.fill(head, -1);

        for (int[] ints : edges) {
            int u = ints[0], v = ints[1];
            add(u,v);
            add(v,u);
        }

        getDownHeightDfs(0,-1);
        getUpHeightDfs(0,-1);

        List<Integer> ans = new ArrayList<>();
        int min = n;
        for (int i = 0; i < n; i++) {
            int cur = Math.max(maxUpHeight[i], maxDownHeight[i]);
            if(cur<min){
                min = cur;
                ans.clear();
                ans.add(i);
            }else if(cur == min){
                ans.add(i);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(maxDownHeight[i]+"-"+secondDownHeight[i]+"-"+maxUpHeight[i]+"-"+p[i]);
        }
        return ans;
    }

    int getDownHeightDfs(int u, int fa){
        for (int i = head[u]; i != -1; i=next[i]) {
            int next = edge[i];
            if(next == fa) continue;

            int t = getDownHeightDfs(next, u) + 1;

            if(t > maxDownHeight[u]){
                secondDownHeight[u] = maxDownHeight[u];
                maxDownHeight[u] = t;

                p[u] = next;
            }else if(t > secondDownHeight[u]){
                secondDownHeight[u] = t;
            }
        }
        return maxDownHeight[u];
    }

    void getUpHeightDfs(int u, int fa){
        for (int i = head[u]; i != -1; i=next[i]) {
            int next = edge[i];
            if(next == fa) continue;

            if(p[u] != next){
                maxUpHeight[next] = Math.max(maxUpHeight[next], maxDownHeight[u]+1);
            }else {
                maxUpHeight[next] = Math.max(maxUpHeight[next], secondDownHeight[u]+1);
            }
            
            maxUpHeight[next] = Math.max(maxUpHeight[u]+1, maxUpHeight[next]);
            getUpHeightDfs(next, u);
        }
    }
}
