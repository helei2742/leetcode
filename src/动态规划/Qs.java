package 动态规划;

import java.util.Arrays;

public class Qs {
    public void qs(int [] arr, int left, int right){
        int l = left, r = right;
        int flag = arr[(left+right)/2];

        while(l<=r){
            while(arr[l] < flag)
                l++;
            while(arr[r] > flag)
                r--;
            if(l<=r){
                int temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
                l++;
                r--;
            }
        }
        System.out.println(Arrays.toString(arr));
        if(r>left) qs(arr,left,r);
        if(l<right) qs(arr,l,right);
    }
}
