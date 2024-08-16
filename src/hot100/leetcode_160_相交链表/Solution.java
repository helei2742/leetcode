package hot100.leetcode_160_相交链表;

import 剑指offer.ListNode;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode ptr1 = headA, ptr2 = headB;

        while(ptr1 != ptr2) {
            ptr1 = ptr1 == null?headB:ptr1.next;
            ptr2 = ptr2 == null?headA:ptr2.next;
        }
        return ptr1;
    }
}
