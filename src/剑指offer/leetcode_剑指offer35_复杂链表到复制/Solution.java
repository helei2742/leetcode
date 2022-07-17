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
