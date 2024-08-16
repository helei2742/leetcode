package hot100.leetcode_297_二叉树的序列化与反序列化;

import 剑指offer.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Solution{

}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";

        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(poll == null){
                sb.append("null,");
            }else {
                sb.append(poll.val).append(",");
                queue.add(poll.left);
                queue.add(poll.right);
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || "[]".equals(data)) return null;
        String s = data.substring(0, data.length()-1);
        String[] strs = s.split(",");
        System.out.println(Arrays.toString(strs));
        TreeNode head = new TreeNode(Integer.parseInt(strs[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);

        int idx = 1;
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(!"null".equals(strs[idx])){
                TreeNode left = new TreeNode(Integer.parseInt(strs[idx]));
                poll.left = left;
                queue.add(left);
            }
            idx++;
            if(!"null".equals(strs[idx])){
                TreeNode right = new TreeNode(Integer.parseInt(strs[idx]));
                poll.right = right;
                queue.add(right);
            }
            idx++;
        }

        return head;
    }
}
