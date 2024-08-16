package hot100.leetcode_142_环形链表II;

import 剑指offer.ListNode;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next;
            }else {
                return null;
            }
            if(fast == slow) {
                slow = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
