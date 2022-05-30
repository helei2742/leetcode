package 图.leetcode_133_克隆图;

import 图.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    private HashMap<Node, Node> v = new HashMap<>();

    public Node cloneGraph(Node node) {
        if(node == null) return null;
        if(v.containsKey(node)) return v.get(node);

        Node newNode = new Node(node.val,new ArrayList<>());
        v.put(node, newNode);
        for (Node neighbor : node.neighbors) { ;
           newNode.neighbors.add(cloneGraph(neighbor));
        }

        return newNode;
    }
}
