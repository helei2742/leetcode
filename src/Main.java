import java.util.Scanner;


public class Main {


    private static int[] numMap = new int[]{1,0,0,0,0,0,1,0,2,1};


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int res = 0;
        while(n/10>0){
            int x = n%10;
            res += numMap[x];
            n /=10;
        }
        res += numMap[n];
        System.out.println(res);
    }
}
