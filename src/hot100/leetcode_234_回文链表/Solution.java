package hot100.leetcode_234_回文链表;

import 剑指offer.ListNode;

class Solution {
    private ListNode head;
    public boolean isPalindrome(ListNode head) {
        this.head = head;
        return fn(head);
    }
    private boolean fn(ListNode node) {
        if(node != null) {
            if(!fn(node.next)){
                return false;
            }
            if(head.val != node.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

}
