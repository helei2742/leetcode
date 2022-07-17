package 剑指offer.leetcode_剑指Offer22链表中倒数第k个节点;

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper;
import 剑指offer.ListNode;

import java.util.Stack;

class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();

        while (head!=null){
            stack.push(head.next);
            head = head.next;
        }

        ListNode res = null;

        while (!stack.empty()){
            res = stack.peek();
            if(--k==0) break;
            stack.pop();
        }
        return res;
    }
}