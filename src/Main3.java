import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] v = new int[m];
        int[] u = new int[m];

        int count = 0;
        while(count < m){
            u[count++] = scanner.nextInt();
        }

        count = 0;
        while(count < m){
            v[count++] = scanner.nextInt();
        }

        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            set.add(v[i] + "-" + u[i]);
            set.add(u[i] + "-" + v[i]);
        }

        int q = scanner.nextInt();
        String[] res = new String[q];
        for (int i = 0; i < q; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if(set.contains(a+"-"+b)||set.contains(b+"-"+a)){
                res[i] = "Yes";
            }else {
                res[i] = "No";
            }
            System.out.println(res[i]);
        }

     }
}
