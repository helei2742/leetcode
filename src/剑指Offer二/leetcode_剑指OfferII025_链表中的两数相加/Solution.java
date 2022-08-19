package 剑指Offer二.leetcode_剑指OfferII025_链表中的两数相加;

import 剑指offer.ListNode;

import java.util.Stack;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();
        while(l1!=null || l2 != null){
            if(l1 != null){
                stack1.add(l1.val);
                l1 = l1.next;
            }
            if(l2 != null){
                stack2.add(l2.val);
                l2 = l2.next;
            }
        }

        int up = 0;
        ListNode res = null;
        while(stack1.size()>0||stack2.size()>0||up!=0){
            int top1 = stack1.size()>0?stack1.pop():0;
            int top2 = stack2.size()>0?stack2.pop():0;

            int tot = top1 + top2 + up;
            ListNode t = new ListNode(tot%10);
            t.next = res;
            res = t;
            up = tot/10;
        }
        return res;
    }

}