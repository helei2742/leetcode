import java.util.*;


public class Main {

    public static void main(String[] args) {
        test();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入有多少个盒子");
        int len = scanner.nextInt();

        System.out.println("请输入纸盒子上的数字，空格隔开");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("请输入目标和");
        int target = scanner.nextInt();
        int res = fun(arr, target);
        System.out.println("结果为："+ (res==-1?"盒子和不足":res));
    }

    public static void test(){
        System.out.println("==================示例测试 start==============");
        int[] arr = new int[]{3,1,4,3,1,2};
        int target = 7;
        System.out.println("盒子为:"+ Arrays.toString(arr));
        System.out.println("目标和为："+target);
        System.out.println("结果为："+fun(arr, target));
        System.out.println("==================示例测试 end==============\n\n");
    }

    /**
     * 求连续和为目标的最小长度，滑动窗口
     * 1、右指针向右滑动，窗口值增加，窗口增大
     * 2、当窗口的值大于等于目标进行窗口的缩小，左指针的向右移动，同时更新窗口值
     * 3、窗口缩小过程中，不断取窗口最小值就为答案
     *
     * 时间复杂度：只会对数组进行一次遍历，为 O（n）
     * 空间复杂度：只需报错窗口的和以及左右指针等，为O（1）
     * @param arr
     * @param maxTarget
     * @return
     */
    public static int fun(int [] arr, int maxTarget) {
        int l = 0, r = 0;
        int window = 0, len = arr.length;
        int res = Integer.MAX_VALUE;
        while(r < len) {
            window += arr[r];
            r++;
            while(l<r && window >= maxTarget) {
                window -= arr[l++];
                res = Math.min(res, r - l + 1);
            }
        }

        return res == Integer.MAX_VALUE?-1:res;
    }



}
