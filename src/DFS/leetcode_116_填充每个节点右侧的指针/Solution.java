package DFS.leetcode_116_填充每个节点右侧的指针;


import DFS.Node;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public Node connect(Node root) {

        if(root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        //层序遍历，每一层逐一链接
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node head = queue.poll();

                if(i < size - 1){
                    head.next = queue.peek();
                }
                if(head.left != null){
                    queue.add(head.left);
                }
                if(head.right != null){
                    queue.add(head.right);
                }
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if(root == null) return null;

        Node cur = root;

        while(cur.left != null) {
            Node head = cur;
            while(head != null) {
                head.left.next = head.right;

                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            cur = cur.left;
        }

        return root;
    }

}