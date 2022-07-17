import java.util.Arrays;

public class QSSort {

    public void qsSort(int left, int right, int[] nums){
        int l = left, r = right;
        int mid = nums[(l+r)/2];
        while (l<=r){

            while (nums[l] <= mid){
                l++;
            }
            while (nums[r] >= mid){
                r--;
            }
            if(l<=r){
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
                l++;
                r--;
            }
        }

        if(l<right) qsSort(l, right, nums);
        if(left<r) qsSort(left,r, nums);
    }

    public static void main(String[] args) {
        QSSort qsSort = new QSSort();

        int[] a = {1,23123,42,332,33,2145,556};
        qsSort.qsSort(0, a.length-1, a);
        System.out.println(Arrays.toString(a));
    }
}
