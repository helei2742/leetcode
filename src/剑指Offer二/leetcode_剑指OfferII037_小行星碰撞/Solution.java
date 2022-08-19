package 剑指Offer二.leetcode_剑指OfferII037_小行星碰撞;

import java.util.Map;
import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int len = asteroids.length;
        stack.push(asteroids[0]);
        for (int i = 1; i < len; i++) {
            int cur = asteroids[i];
            int left = 0;
            boolean curExist = true;
            while(!stack.empty()&&(left = stack.peek()) > 0 && cur < 0){
                int absC = Math.abs(cur);
                int absL = Math.abs(left);
                if(absC < absL) {
                    curExist = false;
                    break;
                }else if(absC == absL){
                    stack.pop();

                    curExist = false;
                    break;
                }else {
                    stack.pop();
                }
            }
            if(curExist){
                stack.push(cur);
            }
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            res[i] = stack.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {5,10,-5};
        solution.asteroidCollision(arr);
    }
}
