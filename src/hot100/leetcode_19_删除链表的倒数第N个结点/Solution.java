package hot100.leetcode_19_删除链表的倒数第N个结点;

import 剑指offer.ListNode;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode ptr = head;
        while(ptr != null) {
            size++;
            ptr = ptr.next;
        }
        if(n == size){
            return head.next;
        }

        ptr = head;
        while(size > n + 1){
            size--;
            ptr = ptr.next;
        }
        ListNode t = ptr.next;
        if(t.next == null) {
            ptr.next = null;
        }else {
            ptr.next = t.next;
        }
        return head;
    }
}
