package 剑指Offer二.leetcode_剑指OfferII027_回文链表;

import 剑指offer.ListNode;

import java.util.List;

class Solution {
    public boolean isPalindrome(ListNode head) {
        front = head;
        return fn(head);
    }
    private ListNode front;
    private boolean fn(ListNode node) {
        if(node != null){
            if (!fn(node.next)) {
                return false;
            }
            if(front.val != node.val) {
                return false;
            }
            front = front.next;
        }
        return true;
    }
}
