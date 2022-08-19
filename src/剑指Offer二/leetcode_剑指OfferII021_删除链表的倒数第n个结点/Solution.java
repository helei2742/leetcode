package 剑指Offer二.leetcode_剑指OfferII021_删除链表的倒数第n个结点;

import 剑指offer.ListNode;

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(-1);
        res.next = head;

        int count = 0;
        ListNode cur = head;
        while(cur != null){
            count++;
            cur = cur.next;
        }
        cur = res;
        while(true){
            if(count--==n){
                ListNode t = cur.next;
                if(t == null){
                    cur.next = t;
                }else {
                    cur.next = t.next;
                }

                break;
            }
            cur = cur.next;
        }
        return res.next;
    }
}
