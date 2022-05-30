import java.lang.reflect.Array;
import java.util.*;

public class 八皇后 {
    private Set<Integer> col;
    private Set<Integer> diagonal1;
    private Set<Integer> diagonal2;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        col = new HashSet<>();
        diagonal1 = new HashSet<>();
        diagonal2 = new HashSet<>();

        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        dfs(n,0,queens);
        return res;
    }

    private void dfs(int n, int row, int[] queens){
        if(row == n){
            res.add(makeAns(n, queens));
        }else {
            for (int i = 0; i < n; i++) {
                if(col.contains(i)) continue;
                if(diagonal1.contains(row-i)) continue;
                if(diagonal2.contains(row+i)) continue;

                col.add(i);
                diagonal1.add(row-i);
                diagonal2.add(row+i);
                queens[row] = i;
                dfs(n, row+1, queens);
                queens[row] = -1;
                diagonal2.remove(row+i);
                diagonal1.remove(row-i);
                col.remove(i);
            }
        }
    }

    private List<String> makeAns(int n, int[] queens){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char [] temp = new char[n];
            Arrays.fill(temp, ',');
            if(queens[i] != -1)
                temp[queens[i]] = 'Q';
            res.add(new String(temp));
        }
        return res;
    }
}
