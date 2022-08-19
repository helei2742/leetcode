package 剑指Offer二.leetcode_剑指OfferII048_序列化与反序列化二叉树;

import 剑指offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
public class Solution{

}
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(poll == null) {
                sb.append("null,");
            }else {
                sb.append(poll.val).append(",");
                queue.add(poll.left);
                queue.add(poll.right);
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("[]".equals(data)) return null;

        String substring = data.substring(1, data.length() - 1);
        String[] strings = substring.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int idx = 1;
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(!"null".equals(strings[idx])){
                poll.left = new TreeNode(Integer.parseInt(strings[idx]));
                queue.add(poll.left);
            }
            idx++;
            if(!"null".equals(strings[idx])){
                poll.right = new TreeNode(Integer.parseInt(strings[idx]));
                queue.add(poll.right);
            }
            idx++;
        }
        return root;
    }
}