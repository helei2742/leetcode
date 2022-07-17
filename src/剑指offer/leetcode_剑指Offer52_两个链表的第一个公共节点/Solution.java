package 剑指offer.leetcode_剑指Offer52_两个链表的第一个公共节点;

import 剑指offer.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
/*        if(headA ==null || headB == null) return null;
        ListNode n1 = headA, n2 = headB;
        while (n1!=n2){
            n1 = (n1==null)?headB:n1.next;
            n2 = (n2==null)?headA:n2.next;
        }
        return n1;*/

        return setMethod(headA, headB);
    }

    private ListNode setMethod(ListNode headA, ListNode headB){
        Set<ListNode> set = new HashSet<>();
        while (headA!=null){
            set.add(headA);
            headA = headA.next;
        }

        while (headB!=null){
            if(set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
}
