import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    private static int[] height;
    private static String[] name;

    private static class People{
        public int height;
        public String name;
        public People(int height, String name){
            this.height = height;
            this.name = name;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        height = new int[n];
        name = new String[n];

        for (int i = 0; i < n; i++) {
            height[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            name[i] = scanner.next();
        }



        List<People> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new People(height[i], name[i]));
        }

        list.sort((p1,p2)->{
            if(p1.height==p2.height){
                return p1.name.compareTo(p2.name);
            }else {
                return p1.height-p2.height;
            }
        });

        for (People people : list) {
            System.out.print(people.name+' ');
        }
    }
}
