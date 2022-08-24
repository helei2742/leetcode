package 剑指offer.leetcode_剑指offer35_复杂链表到复制;

import java.util.HashMap;
import java.util.Map;

class Solution {

    HashMap<Node,Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        if(map.containsKey(head))
            return map.get(head);

        Node newNode = new Node(head.val);
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);

        map.put(head,newNode);
        return newNode;
    }

    public Node copyRandomList2(Node head) {
        Node cur = head;
        while(cur != null) {
            Node temp = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = temp;
            cur = temp;
        }

        cur = head;
        while(cur != null) {
            Node newCur = cur.next;
            newCur.random = cur.random == null?null:cur.random.next;
            cur = cur.next.next;
        }

        Node res = new Node(0);
        Node p = res;
        cur = head;
        while(cur != null) {
            p.next = cur.next;
            p = p.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return res.next;
    }
}
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
