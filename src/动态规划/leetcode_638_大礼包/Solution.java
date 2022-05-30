package 动态规划.leetcode_638_大礼包;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        this.price = price;
        this.special = special;
        this.needs = needs;
        this.n = price.size();
        return dfs(needs);
    }
    private List<Integer> price;
    private List<List<Integer>> special;
    private List<Integer> needs;
    private int n;
    private int dfs(List<Integer> curNeeds){
        int minPrice = 0;
        for (int i = 0; i < n; i++) {
            minPrice += price.get(i) * curNeeds.get(i);
        }
        for (int i = 0; i < special.size(); i++) {
            List<Integer> curSpecial = special.get(i);
            List<Integer> nextNeeds = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if(curSpecial.get(j) > curNeeds.get(j)){
                    break;
                }
                nextNeeds.add(curNeeds.get(j) - curSpecial.get(j));
            }
            if(nextNeeds.size() == n){
                minPrice = Math.min(minPrice, dfs(nextNeeds) + curSpecial.get(n));
            }
        }
        return minPrice;
    }
}
