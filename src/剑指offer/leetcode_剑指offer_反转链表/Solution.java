package 剑指offer.leetcode_剑指offer_反转链表;

import 剑指offer.leetcode_剑指offer06_从尾到头打印链表.ListNode;

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, tail = null;
        if(head == null) return null;

        while(cur.next != null){
            ListNode pres = cur.next;
            cur.next = tail;
            tail = cur;
            cur = pres;
        }
        cur.next=tail;
        return cur;
    }
}
