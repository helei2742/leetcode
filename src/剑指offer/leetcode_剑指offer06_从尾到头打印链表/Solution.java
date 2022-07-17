package 剑指offer.leetcode_剑指offer06_从尾到头打印链表;

import java.util.ArrayList;

class Solution {
    public int[] reversePrint(ListNode head) {
         //1 2 3 4
        if(head == null) return new int[0];
        int total = 0;
        ListNode cur = head, tail = null;
        while(cur.next != null){
            ListNode pre = cur.next;
            cur.next = tail;
            tail = cur;
            cur = pre;
            total++;
        }
        total++;
        cur.next = tail;
        int[] res  = new int[total];
        int count = 0;
        while (cur != null){
            res[count++] = cur.val;
            cur = cur.next;
        }
        return res;
    }
}