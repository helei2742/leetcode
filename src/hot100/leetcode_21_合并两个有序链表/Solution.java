package hot100.leetcode_21_合并两个有序链表;

import 剑指offer.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode ptr = head, ptr1 = list1, ptr2 = list2;

        while(ptr1 != null && ptr2 != null) {
            if(ptr1.val < ptr2.val) {
                ptr.next = ptr1;
                ptr1 = ptr1.next;
            }else {
                ptr.next = ptr2;
                ptr2 = ptr2.next;
            }
            ptr = ptr.next;
        }

        ptr.next = ptr1 == null ? ptr2 : ptr1;
        return head.next;
    }
}
