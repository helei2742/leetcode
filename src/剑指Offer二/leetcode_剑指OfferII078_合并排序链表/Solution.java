package 剑指Offer二.leetcode_剑指OfferII078_合并排序链表;

import 剑指offer.ListNode;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = merge(res, lists[i]);
        }
        return res;
    }

    private ListNode merge(ListNode list1, ListNode list2){
        ListNode head = new ListNode(0);
        ListNode p = head, p1 = list1, p2 = list2;
        while(p1!=null&&p2!=null){
            if(p1.val < p2.val){
                p.next = p1;
                p1 = p1.next;
            }else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        p.next = p1==null?p2:p1;
        return head.next;
    }
}
