package 剑指Offer二.leetcode_剑指OfferII022_链表中环的入口节点;

import 剑指offer.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode pre = new ListNode(0);
        pre.next = head;

        while(pre != null) {
            if(set.contains(pre)){
                return pre;
            }
            set.add(pre);
            pre = pre.next;
        }
        return null;
    }

}
