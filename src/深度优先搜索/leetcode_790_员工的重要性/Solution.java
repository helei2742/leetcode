package 深度优先搜索.leetcode_790_员工的重要性;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
    HashMap<Integer, Employee> idToEmp = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            idToEmp.put(employee.id, employee);
        }
        return dfs(id);
    }
    private int dfs(int id){
        Employee employee = idToEmp.get(id);
        int res = employee.importance;
        List<Integer> subordinates = employee.subordinates;
        for (Integer subordinate : subordinates) {
            res += dfs(subordinate);
        }
        return res;
    }
    private int bfs(int id){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        int ans = 0;
        while(!queue.isEmpty()){
            int curId =  queue.poll();
            Employee employee = idToEmp.get(curId);
            ans+=employee.importance;
            for (Integer subordinate : employee.subordinates) {
                queue.offer(subordinate);
            }
        }
        return ans;
    }
}