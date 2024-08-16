package 剑指Offer二.leetcode_剑指OfferII115_重建序列;

import java.util.*;
class Solution {

    int[] head, edge, next;
    int index;
    private void add(int u, int v) {
        index++;
        edge[index] = v;
        next[index] = head[u];
        head[u] = index;
    }
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        head = new int[nums.length+1];
        edge = new int[nums.length*4];
        next = new int[nums.length*4];

        int[] inDup = new int[nums.length+1];
        Arrays.fill(inDup, -1);
        for (int[] sequence : sequences) {
            if(sequence.length == 1&&inDup[sequence[0]]==-1){
                inDup[sequence[0]] = 0;
                continue;
            }
            for (int i = 1; i < sequence.length; i++) {
                if(inDup[sequence[i]] == -1) inDup[sequence[i]]=0;
                if(inDup[sequence[i-1]] == -1) inDup[sequence[i-1]]=0;
                inDup[sequence[i]]++;
                add(sequence[i-1], sequence[i]);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= nums.length; i++) {
            if(inDup[i] == 0) queue.add(i);
        }
        List<Integer> res = new ArrayList<>();

        while(!queue.isEmpty()) {
            if(queue.size()>1) return false;
            Integer now = queue.poll();
            res.add(now);
            for (int i = head[now]; i != 0; i=next[i]) {
                int next = edge[i];
                if(--inDup[next]==0){
                    queue.add(next);
                }
            }
        }
        if(res.size() < nums.length) return false;
        return true;
    }
}
