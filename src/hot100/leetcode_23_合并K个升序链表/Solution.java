package hot100.leetcode_23_合并K个升序链表;

import 剑指offer.ListNode;


class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if(len == 0) return null;
        if(len == 1) return lists[0];
        head = new ListNode(0);
        ListNode res = merge(lists[0], lists[1]);

        for (int i = 2; i < len; i++) {
            res = merge(res, lists[i]);
        }
        return res;
    }
    private ListNode head;
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode ptr = head, ptr1 = list1, ptr2 = list2;

        while(ptr1 != null && ptr2 != null) {
            if(ptr1.val < ptr2.val){
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
