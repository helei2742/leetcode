package 剑指offer.leetcode_剑指Offer37_序列化二叉树;

import 剑指offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder("[");

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll == null){
                    sb.append("null,");
                }else {
                    sb.append(poll.val).append(",");
                    queue.add(poll.left);
                    queue.add(poll.right);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        System.out.println(sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("[]".equals(data)) return null;
        data = data.substring(1, data.length()-1);
        String[] nodeStrs = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodeStrs[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int idx = 1;
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(!nodeStrs[idx].equals("null")){
                poll.left = new TreeNode(Integer.parseInt(nodeStrs[idx]));
                queue.add(poll.left);
            }
            idx++;
            if(!nodeStrs[idx].equals("null")){
                poll.right = new TreeNode(Integer.parseInt(nodeStrs[idx]));
                queue.add(poll.right);
            }
            idx++;

        }
        return root;
    }
}