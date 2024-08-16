package hot100.leetcode_2_两数相加;

import 剑指offer.ListNode;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ptr1 = l1, ptr2 = l2;
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        int up = 0;
        while(ptr1 != null || ptr2 != null){
            int v1 = 0, v2 = 0;
            if(ptr1 != null){
                v1 = ptr1.val;
                ptr1 = ptr1.next;
            }
            if(ptr2 != null){
                v2 = ptr2.val;
                ptr2 = ptr2.next;
            }
            int t = v1 + v2 + up;
            up = t/10;
            ptr.next = new ListNode(t%10);
            ptr = ptr.next;
        }
        if (up != 0){
            ptr.next = new ListNode(up);
        }
        return head.next;
    }
}
