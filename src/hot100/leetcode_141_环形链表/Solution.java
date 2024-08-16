package hot100.leetcode_141_环形链表;

import 剑指offer.ListNode;

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null|| head.next == null) return false;
        ListNode fast = head, slow = head;
        while(fast.next != null) {
           fast = fast.next;
           slow = slow.next;
           if(fast.next != null) {
               fast = fast.next;
           }
           if(fast == slow) return true;
        }
        return false;
    }
}
