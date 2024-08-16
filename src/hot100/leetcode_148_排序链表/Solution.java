package hot100.leetcode_148_排序链表;

import 剑指offer.ListNode;

class Solution {
    public ListNode sortList(ListNode head) {
        return qs(head, null);
    }

    private ListNode qs(ListNode head, ListNode tail) {
        if(head == null) return null;
        if(head.next == tail){
            head.next = null;
            return head;
        }

        ListNode fast = head, slow = head;
        while(fast.next != tail){
            fast = fast.next;
            slow = slow.next;
            if(fast.next != tail){
                fast = fast.next;
            }
        }

        ListNode left = qs(head, slow);
        ListNode right = qs(slow, tail);

        return merge(left, right);
    }
    private ListNode merge(ListNode list1, ListNode list2) {
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
        ptr.next = ptr1==null?ptr2:ptr1;
        return head.next;
    }
}
