package 剑指Offer二.leetcode_剑指OfferII080_含有k个元素的组合;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        dfs(n, 0, new ArrayList<>(), k);
        return res;
    }

    List<List<Integer>> res;
    private void dfs(int n, int idx, List<Integer> list, int k){
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (list.size() + (n - idx + 1) < k) {
            return;
        }

        if(list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(n == idx) return;

        list.add(idx+1);
        dfs(n, idx+1, list, k);
        list.remove(list.size() - 1);
        dfs(n, idx+1, list, k);

    }
}
