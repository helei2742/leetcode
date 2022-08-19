package 剑指Offer二.leetcode_剑指OfferII006_排序数组中两个数字之和;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0, right = len-1;
        while(left<right){
            if(numbers[left] + numbers[right] == target){
                return new int[]{left, right};
            }else if(numbers[left] + numbers[right] < target){
                left++;
            }else {
                right--;
            }
        }
        return new int[0];
    }

}