package 剑指offer.leetcode_剑指Offer18_删除链表的节点;

import 剑指offer.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val) return head.next;
        ListNode pre = head, cur = head;

        while (cur!=null){
            if(cur.val == val){
                pre.next = cur.next;
                cur = pre.next;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
