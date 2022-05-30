import java.util.Scanner;
import java.util.Stack;

public class Main5 {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] c1 = new int[n];
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        for (int i = 0; i < n; i++) {
            c1[i] = s.nextInt();
            s1.push(c1[i]);
        }
        int m = s.nextInt();
        int[] c2 = new int[m];

        for (int i = 0; i < m; i++) {
            c2[i] = s.nextInt();
            s2.push(c2[i]);
        }

        int res = 0;
        while(!s1.empty()){
            int top1 = s1.peek();
            int top2 = s2.peek();
            if(top1 != top2){
                res++;
                s1.pop();
            }else{
                s1.pop();
                s2.pop();
            }
        }
        res += s2.size();
        System.out.println(res);
    }
}
