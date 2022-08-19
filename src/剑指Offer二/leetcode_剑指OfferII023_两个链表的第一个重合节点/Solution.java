package 剑指Offer二.leetcode_剑指OfferII023_两个链表的第一个重合节点;

import 剑指offer.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        while(headA!=null){
            set.add(headA);
            headA = headA.next;
        }
        while(headB!=null){
            if(set.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    private ListNode fn(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode pA = headA, pB = headB;

        while(pA != pB){
            pA = (pA==null?headB:pA.next);
            pB = (pB==null?headA:pB.next);
        }
        return pA;
    }
}