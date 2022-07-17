package 剑指offer.leetcode_剑指Offer45把数组排成最小的数;

import java.util.Arrays;


public class Solution {


    public String minNumber(int[] nums) {

        numStr = Arrays.stream(nums)
                .mapToObj(String::valueOf).toArray(String[]::new);


        quickSort(0,nums.length-1);
        StringBuilder sb = new StringBuilder();

        for (String num : numStr) {
            sb.append(num);
        }
        return sb.toString();
    }
    String[] numStr = null;

    private void quickSort(int left, int right){
        System.out.println(left);
        int l = left, r = right;
        String flag = numStr[(l+r)/2];
        while (l<=r){
            while ((numStr[l]+flag).compareTo(flag+numStr[l]) < 0){
                l++;
            }
            while ((numStr[r]+flag).compareTo(flag+numStr[r]) > 0){
                r--;
            }
            if(l<=r){
                String t = numStr[l];
                numStr[l] = numStr[r];
                numStr[r] = t;
                l++;
                r--;
            }
        }
        if(r>left) quickSort(left, r);
        if(l<right) quickSort(l, right);
    }
    //搜索绝对超时
/*    public String minNumber(int[] nums) {
        this.len = nums.length;
        dfs(nums,0, "", new boolean[len]);
        return ans;
    }

    String ans = "A";
    int len  = 0;
    private void dfs(int[] nums, int count, String x, boolean[] visited){
        if(count >= len){
            ans = ans.compareTo(x) < 0? ans: x;
            return;
        }
        for (int i = 0; i < len; i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(nums, count+1, x + nums[i], visited);
                visited[i] = false;
            }
        }
    }*/
}
