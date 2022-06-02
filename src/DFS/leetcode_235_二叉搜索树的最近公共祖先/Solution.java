package DFS.leetcode_235_二叉搜索树的最近公共祖先;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list1 = findNodePath(root, p);
        List<TreeNode> list2 = findNodePath(root, q);
        int l1 = list1.size();
        int l2 = list2.size();
        int min = Math.min(l1, l2);
        for (int i = 0; i < min; i++) {
            if(list1.get(l1-i-1) != list2.get(l2 - i-1))return list1.get(l1-i);
        }
        return l2 > l1 ? list1.get(0) : list2.get(0);
    }

    public List<TreeNode> findNodePath(TreeNode root, TreeNode target) {
        if(root == null) return null;

        if(root == target) {
            List<TreeNode> list = new ArrayList<>();
            list.add(target);
            return list;
        }
        List<TreeNode> lList = findNodePath(root.left, target);
        List<TreeNode> rList = findNodePath(root.right, target);
        if(lList != null) {
            lList.add(root);
            return lList;
        }else if (rList != null) {
            rList.add(root);
            return rList;
        }else {
            return null;
        }
    }

    /**
     * 二叉搜索树，使用性质遍历
     * @param root
     * @param target
     * @return
     */
    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while(true) {
            path.add(node);
            if (target.val < node.val){
                node = node.left;
            }else if (target.val > node.val) {
                node = node.right;
            }else break;
        }
        path.add(node);
        return path;
    }

    /**
     * 当p节点和q节点都小于当前节点时，不能确定是最近公共的祖先，祖先节点变为左儿子继续比较
     * 当一个节点大于当前节点，一个节点等于当前节点时，说明当前节点就是祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }


    private TreeNode ans;
    /**
     * 非二叉搜索树的方法
     * @param root
     * @param p
     * @param q
     * @return 返回当前root节点中是否含有p或q节点
     */
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        //代表左边含有p或q节点
        boolean lson = dfs(root.left, p, q);
        //代表右边含有p或q节点
        boolean rson = dfs(root.right, p, q);
        /**
         * 情况1，当左边含有p或q节点，由于是从下往上递归，说明当前的root节点就是最近的最近公共祖先
         * 情况2，当当前root节点等于p时， 只要左边或者右边存在另一个节点q，就说明当前的节点为最近公共祖先
         */
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

}
