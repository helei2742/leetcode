package 剑指Offer二.leetcode_剑指OfferII077_链表排序;

import 剑指offer.ListNode;

class Solution {
    public ListNode sortList(ListNode head) {
        return quickSort(head, null);
    }

    private ListNode quickSort(ListNode head, ListNode tail) {
        if(head == null) return null;
        if(head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode show = head, fast = head;
        while(fast != tail){
            show = show.next;
            fast = fast.next;
            if(fast != tail){
                fast = fast.next;
            }
        }

        ListNode left = quickSort(head, show);
        ListNode right = quickSort(show, fast);
        return merge(left, right);
    }
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode pointer = head, pointer1 = list1, pointer2 = list2;
        while(pointer1 != null && pointer2 != null){
            if(pointer1.val < pointer2.val) {
                pointer.next = pointer1;
                pointer1 = pointer1.next;
            }else {
                pointer.next = pointer2;
                pointer2 = pointer2.next;
            }
            pointer = pointer.next;
        }
        pointer.next = pointer1 == null? pointer2: pointer1;
        return head.next;
    }
}
