import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 旋转输出数组 {
    private static void fn(int [][] arr){
        int row = arr.length;
        int col = arr[0].length;
        int times = Math.min(row, col);
        if(times%2==0) times--;
        times--;
        System.out.println(times);
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        System.out.println(times);
        while(count < times){
            System.out.println(count);
            for(int i = count; i < col -count; i++){
                ans.add(arr[count][i]);
            }
            for(int i = count + 1; i < row - count; i++){
                ans.add(arr[i][col-count -1]);
            }
            for(int i = col - count -2; i > count; i--){
                ans.add(arr[row - count-1][i]);
            }
            for(int i = row - count -1; i > count; i--){
                ans.add(arr[i][count]);
            }
            count++;
            if(count==times) break;
        }
        if(row > col) ans.remove(ans.size()-1);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int [][] x ={{1,2,3,4},{6,7,8,9},{11,12,13,14}};
        for (int i = 0; i < x.length; i++) {
            System.out.println(Arrays.toString(x[i]));
        }
        System.out.println("--------");
        fn(x);
    }
}
