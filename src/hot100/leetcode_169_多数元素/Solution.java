package hot100.leetcode_169_多数元素;

class Solution {
    public int majorityElement(int[] nums) {
        int res = 0, count = 0;

        for (int num : nums) {
            if(count == 0){
                res = num;
                count++;
            }
            if(res == num){
                count++;
            }else {
                count--;
                if(count == 0){
                    res = num;
                    count++;
                }
            }
        }
        return res;
    }
}
