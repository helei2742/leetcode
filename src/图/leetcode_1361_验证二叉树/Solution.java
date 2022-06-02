package 图.leetcode_1361_验证二叉树;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indup = new int[n];
        for (int i = 0; i < n; i++) {
            if(leftChild[i] != -1){
                graph.get(i).add(leftChild[i]);
                indup[leftChild[i]]++;
            }
            if(rightChild[i] != -1){
                graph.get(i).add(rightChild[i]);
                indup[rightChild[i]]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if(indup[i] == 0) queue.add(i);
            if(indup[i] > 1) return false;
        }
        if(queue.size() > 1) return false;
        int count = 0;
        while (!queue.isEmpty()){
            int now = queue.poll();
            count++;
            for (Integer next : graph.get(now)) {
                if(--indup[next] == 0){
                    queue.add(next);
                }
            }
        }
        return count == n;
    }
}