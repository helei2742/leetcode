package 并查集.leetcode_721_账户合并;

import java.util.*;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /*
            建立邮箱和其的索引的映射
            和邮箱和对应名字的映射
        * */
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        int emailCount = 0;
        //初始化映射
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email, emailCount++);
                    emailToName.put(email, name);
                }
            }
        }

        UnionFind unionFind = new UnionFind(emailCount + 1);

        /*
            将邮箱根据索引加入并查集，
            这样，具有重复邮箱的账户的邮箱们会被加入到一个集中
        * */
        for (List<String> account : accounts) {
            String first = account.get(1);
            int fIndex = emailToIndex.get(first);

            int size = account.size();
            for (int i = 2; i < size; i++) {
                String email = account.get(i);
                int nIndex = emailToIndex.get(email);

                unionFind.union(fIndex, nIndex);
            }
        }

        //创建并查集的根节点的索引与 其拥有的节点邮箱名的映射，并且这些邮箱在一起说明是同一个人的
        Map<Integer, ArrayList<String>> rootToEmails = new HashMap<>();
        /*
            遍历所有的邮箱，根据邮箱名找到引索，再根据索引在并查集中找到其根节点
            填入映射中
        * */
        for (String email : emailToIndex.keySet()) {
            int root = unionFind.findF(emailToIndex.get(email));
            ArrayList<String> emails = rootToEmails.getOrDefault(root, new ArrayList<String>());
            emails.add(email);
            rootToEmails.put(root, emails);
        }

        List<List<String>> ans = new ArrayList<>();
        for (ArrayList<String> value : rootToEmails.values()) {
            //取出相连的邮箱们，并在邮箱和名字的映射中取出名字，按要求填入
            Collections.sort(value);

            String name = emailToName.get(value.get(0));
            List<String> account = new ArrayList<>();

            account.add(name);
            account.addAll(value);

            ans.add(account);
        }

        return ans;
    }
    class UnionFind {

        private final int[] father;
        UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x) {
            return x != father[x] ? father[x] = findF(father[x]) : x;
        }
        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            father[fA] = fB;
        }
    }

}
