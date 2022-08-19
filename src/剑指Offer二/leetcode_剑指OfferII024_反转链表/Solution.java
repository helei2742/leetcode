package 剑指Offer二.leetcode_剑指OfferII024_反转链表;

import 剑指offer.ListNode;

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;

        while(cur != null){
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }
}